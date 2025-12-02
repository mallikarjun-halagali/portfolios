package com.mallikarjun.user_mngt.repository;

import com.mallikarjun.user_mngt.model.UserNotificationPreferences;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationPreferencesRepository
        extends ReactiveCrudRepository<UserNotificationPreferences, Long> {

}
