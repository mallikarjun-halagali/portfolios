//package com.mallikarjun.portfolios.service.externalAPI;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@Service
//public class StockService {
//
//    private final WebClient client;
//
//    @Value("${finnhub.api-key}")
//    private String apiKey;
//
//    public StockService(WebClient finnhubWebClient) {
//        this.client = finnhubWebClient;
//    }
//
//    public Mono<String> getStockQuote(String symbol) {
//        return client.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/quote")
//                        .queryParam("symbol", symbol)
//                        .queryParam("token", apiKey)
//                        .build())
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//}
