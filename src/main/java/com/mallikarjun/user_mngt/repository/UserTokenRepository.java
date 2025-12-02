package com.mallikarjun.user_mngt.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserTokenRepository<UserToken> extends ReactiveCrudRepository<UserToken, Long> {

    // Example custom query: find tokens by user ID
    Flux<UserToken> findByUserId(Long userId);

    // Optional: find active tokens by type
    Flux<UserToken> findByUserIdAndType(Long userId, String type);
}
