package com.yomounew.user.application.dto.requests;

import lombok.Data;

@Data
public class LoginRequest {
    //userName or email
    private final String userIdentifier;
    private final String password;
}
