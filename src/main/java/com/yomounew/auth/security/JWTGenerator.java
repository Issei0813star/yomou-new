package com.yomounew.auth.security;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTGenerator {
    private final SecretKeyManager secretKeyManager;

    public String generateToken(String userName, String email) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 864000000);

        SecretKey key = secretKeyManager.getSecretKey();

        return Jwts.builder()
                .setExpiration(expirationDate)
                .claim("user_name", userName)
                .claim("email", email)
                .signWith(key)
                .compact();
    }
}
