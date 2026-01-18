package com.mallikarjun.portfolios.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@RequiredArgsConstructor
public class ApiSchedulerService {

    private final WebClient webClient; // or WebClient for reactive
    private final ReactiveMongoTemplate mongoTemplate;
    private List<String> failedData = new ArrayList<>();


    // Runs every day at 8 PM
    @Scheduled(cron = "0 0 20 * * ?")
    public void dailyApiCall() {
        System.out.println("Running daily API call...");

        // Call the API
        Map<String, List<String>> response = callApi(getDistinctStockNames().block());

        // Example: response contains "success" and "failed" lists
        List<String> failed = (List<String>) response.get("FAILED");
        if (failed != null) {
            failedData.clear();
            failedData.addAll(failed);
        }

        // Schedule retry if failed data exists
        retryFailed();
    }

    public Mono<Void> dailyApiCallReactive() {
        return getDistinctStockNames()
                .flatMap(tickers -> {
                    if (tickers.isEmpty()) return Mono.empty();

                    return webClient.post()
                            .uri("/ai/news/all")
                            .body(BodyInserters.fromValue(tickers))
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<Map<String, List<String>>>() {})
                            .doOnNext(response -> {
                                List<String> failed = response.get("FAILED");
                                failedData.clear();
                                if (failed != null) failedData.addAll(failed);
                            })
                            .then(); // Mono<Void>
                });
    }


    // Retry every 10 min until failedData is empty
    @Scheduled(fixedDelay = 10 * 60 * 1000) // every 10 minutes
    public void retryFailed() {
        if (failedData.isEmpty()) return;

        System.out.println("Retrying failed data: " + failedData);

        // Call API with only failed data
        Map<String, List<String>> response = callApi(failedData);

        List<String> stillFailed = (List<String>) response.get("FAILED");
        failedData.clear();
        if (stillFailed != null) {
            failedData.addAll(stillFailed);
        }
    }

    private Map<String, List<String>> callApi(List<String> tickers) {
        // Example API call
        // Replace with actual API endpoint
         return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/ai/news/all")
                        .build())
                .body(BodyInserters.fromValue(tickers))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, List<String>>>() {}).block();

    }

    public Mono<List<String>> getDistinctStockNames() {
        // Unwind the stocksList array
        UnwindOperation unwind = unwind("stocksList");

        // Group by stock name to get distinct values
        Aggregation aggregation = newAggregation(
                unwind,
                group("stocksList.isin")
        );

//        Flux<String> results = mongoTemplate.aggregate(
//                aggregation,
//                "portfolio",  // name of your collection
//                String.class
//        );
        Flux<String> results = mongoTemplate.aggregate(aggregation, "portfolio", Map.class)
                .map(doc -> doc.get("_id").toString()); // extract only the _id

        // Extract distinct names from aggregation result
        return results.collectList();
//                .map(Object::toString);
    }

}
