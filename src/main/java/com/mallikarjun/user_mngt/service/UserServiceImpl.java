package com.mallikarjun.user_mngt.service;

import com.mallikarjun.user_mngt.model.User;
import com.mallikarjun.user_mngt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public Mono<?> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<?> createUser(User user) {
        return userRepository.save(user);
    }
}
