package com.yomounew.user.application.dto.requests;

import lombok.Data;

@Data
public class UserCreateRequest {
    private final String userName;
    private final String email;
    private final String password;
}
