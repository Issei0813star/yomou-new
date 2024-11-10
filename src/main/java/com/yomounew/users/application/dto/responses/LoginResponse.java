package com.yomounew.users.application.dto.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private final Long userId;
    private final String token;
}
