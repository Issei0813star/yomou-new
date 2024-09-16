package com.yomounew.auth.application.service.impl;

import com.yomounew.auth.application.service.TokenService;
import com.yomounew.auth.security.SecretKeyManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final SecretKeyManager secretKeyManager;

    @Override
    public String getUserName(String token) {
        try {
            Claims claims = getClaims(token);
            return (String) claims.get("user_name");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Claims getClaims(String token) {
        try{
            SecretKey key = this.secretKeyManager.getSecretKey();

            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return jws.getBody();
        }
        catch(Exception e){
            //TODO エラーハンドリング
            e.printStackTrace();
            throw e;
        }
    }
}
