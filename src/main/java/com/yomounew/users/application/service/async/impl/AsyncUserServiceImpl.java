package com.yomounew.users.application.service.async.impl;

import com.yomounew.users.application.service.async.AsyncUserService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncUserServiceImpl implements AsyncUserService {

    public CompletableFuture<Void> asyncSendEmail(Long Userid) {
        return CompletableFuture.completedFuture(null);
    }
}
