package com.mallikarjun.user_mngt.repository;

import com.mallikarjun.user_mngt.model.UserProfile;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface UserProfileRepository extends ReactiveCrudRepository<UserProfile, Long> {

    // Find profile by user ID
    Mono<UserProfile> findByUserId(Long userId);
}
