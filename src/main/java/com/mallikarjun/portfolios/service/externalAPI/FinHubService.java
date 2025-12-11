package com.mallikarjun.portfolios.service.externalAPI;

import com.github.benmanes.caffeine.cache.Cache;
import com.mallikarjun.portfolios.model.FinHubStockQuote;
import com.mallikarjun.portfolios.model.FinHubStocks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FinHubService {

    private final WebClient finnhubWebClient;
    private final Cache<String, FinHubStockQuote> stockQuoteCache;


    public Mono<?> getStockSymbol(String stockSymbol) {

        return finnhubWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stock/symbol")
                        .queryParam("q", stockSymbol)
                        .build())
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
                .retrieve()
                .bodyToMono(FinHubStockQuote.class)
                .doOnNext(quote -> stockQuoteCache.put(isin, quote));
    }
}
