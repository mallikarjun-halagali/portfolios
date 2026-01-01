package com.mallikarjun.portfolios.service.externalAPI;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsightService {

    private final WebClient webClient;

    public Object getStockInsights(String ticker) {
        // Placeholder implementation
        log.info("Fetching insights for ticker: {}", ticker);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/news")
                        .queryParam("ticker", ticker)
                        .build())
                .retrieve()
                .bodyToMono(Object.class).block();
    }
}
