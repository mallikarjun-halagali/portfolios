package com.mallikarjun.portfolios.repository;

import com.mallikarjun.portfolios.model.PortfolioProfile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends ReactiveMongoRepository<PortfolioProfile, String> {
}
