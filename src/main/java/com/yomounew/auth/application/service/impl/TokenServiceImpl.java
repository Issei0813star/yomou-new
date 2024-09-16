package com.yomounew.auth.application.service.impl;

import com.yomounew.auth.application.service.TokenService;
import com.yomounew.auth.security.SecretKeyManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

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

    @Override
    public Claims getClaims(String token) {
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

    @Override
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
