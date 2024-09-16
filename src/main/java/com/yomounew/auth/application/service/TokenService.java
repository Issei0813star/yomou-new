package com.yomounew.auth.application.service;

import io.jsonwebtoken.Claims;

public interface TokenService {
    public String getUserName(String token);
    public Claims getClaims(String token);
    public String generateToken(String userName, String email);
}
