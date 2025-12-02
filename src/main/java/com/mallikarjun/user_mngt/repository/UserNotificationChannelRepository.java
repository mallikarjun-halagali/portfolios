package com.mallikarjun.user_mngt.repository;

import com.mallikarjun.user_mngt.model.UserNotificationChannel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserNotificationChannelRepository
        extends ReactiveCrudRepository<UserNotificationChannel, Long> {

    // Find all channels for a user
    Flux<UserNotificationChannel> findByUserId(Long userId);

    // Find only verified channels
    Flux<UserNotificationChannel> findByUserIdAndIsVerified(Long userId, Boolean isVerified);

    // Find primary channel of a type (e.g. email, sms)
    Flux<UserNotificationChannel> findByUserIdAndTypeAndIsPrimary(Long userId, String type, Boolean isPrimary);
}
