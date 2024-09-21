package com.yomounew.user.application.service;

import com.yomounew.user.application.dto.requests.LoginRequest;
import com.yomounew.user.application.dto.requests.UserCreateRequest;
import com.yomounew.user.application.dto.responses.LoginResponse;
import com.yomounew.user.application.dto.responses.UserCreateResponse;

public interface UserService {

    public LoginResponse login(LoginRequest req);

    public UserCreateResponse createUser(UserCreateRequest req);

}