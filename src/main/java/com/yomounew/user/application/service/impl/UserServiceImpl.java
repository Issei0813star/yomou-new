package com.yomounew.user.application.service.impl;

import com.yomounew.user.application.dto.requests.LoginRequest;
import com.yomounew.user.application.dto.responses.LoginResponse;
import com.yomounew.user.application.service.UserService;
import com.yomounew.user.domain.model.entity.User;
import com.yomounew.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest req) {

        if(StringUtils.isBlank((req.getUserIdentifier()))) {
            //TODO カスタム例外
            throw new IllegalStateException("userName or email required");
        }

        if(StringUtils.isBlank(req.getPassword())) {
            //TODO カスタム例外
            throw new IllegalStateException();
        }

        User user = userRepository.findUserByUserNameOrEmail(req.getUserIdentifier());
        if(!user.getVerified()) {
            //TODO カスタム例外
            throw new IllegalStateException();
        }
        if(!user.getPassword().equals(req.getPassword())) {
            //TODO カスタム例外
            throw new IllegalStateException();
        }

        //TODO token生成
        return new LoginResponse(user.getId(), "dummyToken");
    }

}
