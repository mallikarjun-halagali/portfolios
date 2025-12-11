package com.mallikarjun.portfolios.service.externalAPI;

import com.mallikarjun.portfolios.model.FinHubStocks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FinHubService {

    private final WebClient finnhubWebClient;


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
}
