package com.mallikarjun.user_mngt.controller;

import com.mallikarjun.user_mngt.model.User;
import com.mallikarjun.user_mngt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController("users/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get/{id}")
    Mono<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);

    }

    @PostMapping("/create")
    Mono<?> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
