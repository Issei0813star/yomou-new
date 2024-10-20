package com.yomounew.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserDto {

    private final Long id;
    private final String userName;
}
