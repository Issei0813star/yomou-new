package com.yomounew.user.application.service;

import com.yomounew.user.application.dto.requests.LoginRequest;
import com.yomounew.user.application.dto.responses.LoginResponse;

public interface UserService {

    public LoginResponse login(LoginRequest req);

}