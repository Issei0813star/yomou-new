package com.yomounew.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final List<String> excludePaths = List.of("auth/login", "user/create");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if(this.isExcludePath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        //TODO jwt認証
    }

    private boolean isExcludePath(String path) {
        return this.excludePaths.contains(path);
    }
}
