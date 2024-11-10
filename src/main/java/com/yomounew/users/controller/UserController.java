package com.yomounew.users.controller;

import com.yomounew.users.application.dto.requests.LoginRequest;
import com.yomounew.users.application.dto.requests.UserCreateRequest;
import com.yomounew.users.application.dto.responses.LoginResponse;
import com.yomounew.users.application.dto.responses.UserCreateResponse;
import com.yomounew.users.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return userService.login(req);
    }

    @PostMapping
    public UserCreateResponse createUser(@RequestBody UserCreateRequest req) {
        return userService.createUser(req);
    }
}
