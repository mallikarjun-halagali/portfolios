package com.mallikarjun.user_mngt.service;

import com.mallikarjun.user_mngt.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
     Mono<?> getUserById(Long id);

     Mono<?> createUser(User user);
}
