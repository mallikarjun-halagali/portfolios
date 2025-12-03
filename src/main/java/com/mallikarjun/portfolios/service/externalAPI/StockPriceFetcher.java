package com.mallikarjun.portfolios.service.externalAPI;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class StockPriceFetcher {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Mono<Double> getStockPrice(String symbol) {
        String url = "/v7/finance/quote?symbols=" + symbol;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parsePrice);
    }

    private Double parsePrice(String response) {
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode result = root.path("quoteResponse").path("result");
            if (result.isArray() && result.size() > 0) {
                return result.get(0).path("regularMarketPrice").asDouble();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
