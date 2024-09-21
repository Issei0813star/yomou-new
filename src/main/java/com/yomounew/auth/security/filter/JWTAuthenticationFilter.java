package com.yomounew.auth.security.filter;

import com.yomounew.auth.application.constant.AuthKeys;
import com.yomounew.auth.application.service.TokenService;
import com.yomounew.exception.YomouException;
import com.yomounew.exception.YomouMessage;
import io.jsonwebtoken.Claims;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final List<String> excludePaths = List.of("/user/login", "/user/create");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if(this.isExcludePath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(request.getHeader(AuthKeys.KEY_AUTHORIZATION));

        try {
            Claims claims = tokenService.getClaims(token);
            if(StringUtils.isNotBlank(token) && Objects.nonNull(claims)) {
                Authentication authentication = createAuthentication(claims);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }
            else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Authentication error: " + e.getMessage());
        }

    }

    private boolean isExcludePath(String path) {
        return this.excludePaths.contains(path);
    }

    private String extractToken (String bearer){
        if(Objects.isNull(bearer) || !bearer.startsWith("Bearer ")){
            throw new YomouException(YomouMessage.INVALID_TOKEN, bearer);
        }
        return bearer.substring(7);
    }

    private Authentication createAuthentication(Claims claims) {
        String userName = (String) claims.get("user_name");
        UserDetails user = new CustomUser(userName, "email");
        return new UsernamePasswordAuthenticationToken(user, null, null);
    }

    @Getter
    public static class CustomUser extends User {

        private final String email;

        public CustomUser(String userName, String email) {
            super(userName, "", emptyList());
            this.email = email;
        }

    }
}
