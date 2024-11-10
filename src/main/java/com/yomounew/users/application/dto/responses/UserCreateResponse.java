package com.yomounew.users.application.dto.responses;

import lombok.Data;

@Data
public class UserCreateResponse {
    private final Long userId;
    private final String userName;
    private final String email;
}
