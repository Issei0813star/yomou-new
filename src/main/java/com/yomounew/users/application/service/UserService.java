package com.yomounew.users.application.service;

import com.yomounew.users.application.dto.requests.LoginRequest;
import com.yomounew.users.application.dto.requests.UserCreateRequest;
import com.yomounew.users.application.dto.responses.LoginResponse;
import com.yomounew.users.application.dto.responses.UserCreateResponse;

public interface UserService {

    public LoginResponse login(LoginRequest req);

    public UserCreateResponse createUser(UserCreateRequest req);

}