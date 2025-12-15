package com.mallikarjun.portfolios.service.externalAPI;

import com.github.benmanes.caffeine.cache.Cache;
import com.mallikarjun.portfolios.model.FinHubStockQuote;
import com.mallikarjun.portfolios.model.FinHubStocks;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinHubService {

//    @Value("${finnhub.api-key}")
//    private String finnhubApiKey;

    private final WebClient finnhubWebClient;
    private final Cache<String, FinHubStockQuote> stockQuoteCache;


    public Mono<Object> searchStockSymbol(String query) {
        return finnhubWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", query)
                        .build())
//                .header("X-Finnhub-Token", finnhubApiKey)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Flux<FinHubStocks> getAllStocks() {

        return finnhubWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stock/symbol")
                        .queryParam("exchange", "US")
                        .queryParam("mic", "XNYS")
                        .build())
                .retrieve()
                .bodyToFlux(FinHubStocks.class);

    }

    public Mono<FinHubStockQuote> stockQuote(String isin) {
        FinHubStockQuote cachedQuote = stockQuoteCache.getIfPresent(isin);
        if (cachedQuote != null) {
            return Mono.just(cachedQuote);
        }
        return finnhubWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/quote")
                        .queryParam("symbol", isin)
                        .build())
//                .header("X-Finnhub-Token",finnhubApiKey)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(
                                        new RuntimeException("FinHub error: " + body)
                                ))
                )
                .bodyToMono(FinHubStockQuote.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)).jitter(0.5).doAfterRetry(retrySignal -> log.info("retrying...")))
                .doOnNext(quote -> stockQuoteCache.put(isin, quote))
                .timeout(Duration.ofSeconds(30));
    }
}
