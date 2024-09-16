package com.yomounew.auth.security;

import jakarta.annotation.PostConstruct;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SecretKeyManager {

    private SecretKey secretKey;

    @PostConstruct
    private void init() {
        String secretKeyStr = generateSecretKey();
        generate(secretKeyStr);
    }

    private String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    private void generate(String secretKeyStr) {
        byte[] secretKeyBytes = secretKeyStr.getBytes();
        this.secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
    }
}
