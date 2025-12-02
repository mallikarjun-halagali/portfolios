package com.mallikarjun.user_mngt.repository;

import com.mallikarjun.user_mngt.model.BrokerAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BrokerAccountRepository extends ReactiveCrudRepository<BrokerAccount, Long> {

    // All accounts for a user
    Flux<BrokerAccount> findByUserId(Long userId);

    // Filter by provider (e.g., "alpaca", "binance", "ibkr")
    Flux<BrokerAccount> findByUserIdAndProvider(Long userId, String provider);

    // Optionally: get accounts by status
    Flux<BrokerAccount> findByStatus(String status);
}
