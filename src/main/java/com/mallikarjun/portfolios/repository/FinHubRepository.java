package com.mallikarjun.portfolios.repository;

import com.mallikarjun.portfolios.model.FinHubStocks;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FinHubRepository extends ReactiveMongoRepository<FinHubStocks, String> {
}
