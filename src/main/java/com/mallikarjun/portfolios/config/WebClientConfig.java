package com.mallikarjun.portfolios.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.reactor.ratelimiter.operator.RateLimiterOperator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Value("${finnhub.base-url}")
    private String finnhubBaseUrl;

    @Value("${finnhub.api-key}")
    private String finnHubApiKey;

//    @Bean
//    public WebClient yahooClient(WebClient.Builder webClientBuilder) {
//        return webClientBuilder
//                .baseUrl("https://query1.finance.yahoo.com") // Set your base URL here
//                .defaultHeader("Accept", "application/json") // Example: Set default Accept header
//                .build();
//    }
    @Bean
    public WebClient yahooClient() {
        return WebClient.builder()
                .baseUrl("https://query1.finance.yahoo.com")
                .defaultHeader(HttpHeaders.USER_AGENT,
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .build();
    }

    @Bean
    public WebClient finnhubWebClient(RateLimiter finnhubRateLimiter) {
        return WebClient.builder()
                .baseUrl(finnhubBaseUrl)
                .defaultHeader("X-Finnhub-Token", finnHubApiKey)
                .filter(rateLimiterFilter(finnhubRateLimiter))
                .build();
    }

    private ExchangeFilterFunction rateLimiterFilter(RateLimiter rateLimiter) {
        return (request, next) ->
                next.exchange(request)
                        .transformDeferred(RateLimiterOperator.of(rateLimiter));
    }

}
