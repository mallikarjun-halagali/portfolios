package com.mallikarjun.portfolios.repository;

import com.mallikarjun.portfolios.model.Portfolio;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PortfolioRepository extends ReactiveMongoRepository<Portfolio, String> {

    Mono<Portfolio> findByPortfolioId(String portfolioId);

    Mono<Portfolio> getByUserId(String userId);
}
