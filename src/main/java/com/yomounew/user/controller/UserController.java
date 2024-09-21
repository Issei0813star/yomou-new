package com.yomounew.user.controller;

import com.yomounew.user.application.dto.requests.LoginRequest;
import com.yomounew.user.application.dto.requests.UserCreateRequest;
import com.yomounew.user.application.dto.responses.LoginResponse;
import com.yomounew.user.application.dto.responses.UserCreateResponse;
import com.yomounew.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(LoginRequest req) {
        return userService.login(req);
    }

    @PostMapping
    public UserCreateResponse createUser(UserCreateRequest req) {
        return userService.createUser(req);
    }
}
