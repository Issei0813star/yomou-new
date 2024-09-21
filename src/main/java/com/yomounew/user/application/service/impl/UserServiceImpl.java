package com.yomounew.user.application.service.impl;

import com.yomounew.auth.application.service.TokenService;
import com.yomounew.auth.security.PasswordHashingUtil;
import com.yomounew.exception.YomouException;
import com.yomounew.exception.YomouMessage;
import com.yomounew.user.application.dto.requests.LoginRequest;
import com.yomounew.user.application.dto.requests.UserCreateRequest;
import com.yomounew.user.application.dto.responses.LoginResponse;
import com.yomounew.user.application.dto.responses.UserCreateResponse;
import com.yomounew.user.application.service.UserService;
import com.yomounew.user.domain.model.entity.User;
import com.yomounew.user.domain.repository.UserRepository;
import com.yomounew.user.utils.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordHashingUtil passwordHashingUtil;

    public LoginResponse login(LoginRequest req) {
        validateLoginReq(req);
        User user = userRepository.findUserByUserNameOrEmail(req.getUserIdentifier());
        if(Objects.isNull(user)) throw new YomouException(YomouMessage.USER_NOT_FOUND, req.getUserIdentifier());

        if(Boolean.FALSE.equals(user.getVerified())) {
            //TODO 認証メール再送
            throw new YomouException(YomouMessage.USER_NOT_VERIFIED, user.getUserName());
        }

        if(Boolean.FALSE.equals(passwordHashingUtil.verifyPassword(req.getPassword(), user.getPassword()))) throw new YomouException(YomouMessage.INCORRECT_PASSWORD, req.getPassword());

        String token = tokenService.generateToken(user.getUserName(), user.getEmail(), user.getId());
        return new LoginResponse(user.getId(), token);
    }

    private void validateLoginReq(LoginRequest req) {
        if(StringUtils.isBlank((req.getUserIdentifier()))) throw new YomouException(YomouMessage.USER_NAME_OR_EMAIL_REQUIRED, req);
        if(StringUtils.isBlank(req.getPassword())) throw new YomouException(YomouMessage.PASSWORD_IS_REQUIRED, req);
    }

    @Override
    public UserCreateResponse createUser(UserCreateRequest req) {

        validateCreateReq(req);

        User user = new User();
        user.setUserName(req.getUserName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordHashingUtil.encodePassword(req.getPassword()));

        User createdUser = userRepository.save(user);
        //TODO 認証メール送信

        return new UserCreateResponse(createdUser.getId(), createdUser.getUserName(), createdUser.getEmail());
    }

    private void validateCreateReq(UserCreateRequest req) {
        //ユーザー名
        String userName = req.getUserName();
        if(StringUtils.isBlank(userName)) throw new YomouException(YomouMessage.USER_NAME_IS_REQUIRED, req);
        if(Boolean.TRUE.equals(userRepository.existsByEmail(userName))) throw new YomouException(YomouMessage.USER_NAME_NOT_UNIQUE, req);
        if(userName.length() < 4 || userName.length() > 30) throw new YomouException(YomouMessage.USER_NAME_LENGTH_INVALID, req);

        //メールアドレス
        String email = req.getEmail();
        if(StringUtils.isBlank(email))  throw new YomouException(YomouMessage.EMAIL_IS_REQUIRED, req);
        if(Boolean.TRUE.equals(userRepository.existsByEmail(email))) throw new YomouException(YomouMessage.EMAIL_NOT_UNIQUE, req);
        if(Boolean.FALSE.equals(EmailValidator.isValidEmail(email))) throw new YomouException(YomouMessage.EMAIL_IS_INVALID, req);

        //パスワード
        String password = req.getPassword();
        if(StringUtils.isBlank(password)) throw new YomouException(YomouMessage.PASSWORD_IS_REQUIRED, req);
        if(password.length() < 8 || password.length() > 20) throw new YomouException(YomouMessage.PASSWORD_LENGTH_INVALID, req);
    }
}
