package com.yomounew.users.application.service.async;

import java.util.concurrent.CompletableFuture;

public interface AsyncUserService {
    public CompletableFuture<Void> asyncSendEmail(Long userId);
}
