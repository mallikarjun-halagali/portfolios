package com.mallikarjun.portfolios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
//                .baseUrl("http://localhost:8080/api/v1") // Set your base URL here
                .defaultHeader("Accept", "application/json") // Example: Set default Accept header
                .build();
    }

}
