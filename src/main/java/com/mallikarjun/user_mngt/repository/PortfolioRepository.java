package com.mallikarjun.user_mngt.repository;

import com.mallikarjun.user_mngt.model.Portfolio;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends ReactiveCrudRepository<Portfolio, Long> {
}
