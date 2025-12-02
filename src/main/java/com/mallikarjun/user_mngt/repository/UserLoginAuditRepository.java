package com.mallikarjun.user_mngt.repository;

import com.mallikarjun.user_mngt.model.UserLoginAudit;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserLoginAuditRepository extends ReactiveCrudRepository<UserLoginAudit, Long> {

    // Get all login attempts by a user
    Flux<UserLoginAudit> findByUserId(Long userId);

    // Get only successful logins
    Flux<UserLoginAudit> findByUserIdAndSuccess(Long userId, Boolean success);
}