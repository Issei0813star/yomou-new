package com.yomounew.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum YomouMessage {

    USER_NOT_FOUND("ユーザーが存在しません。", HttpStatus.NOT_FOUND),
    INCORRECT_PASSWORD("パスワードが間違っています。", HttpStatus.UNAUTHORIZED),
    EMAIL_NOT_UNIQUE("指定されたメールアドレスは既に登録されています。", HttpStatus.CONFLICT),
    INVALID_TOKEN("トークンが不正", HttpStatus.BAD_REQUEST),
    USER_NAME_OR_EMAIL_REQUIRED("ユーザー名もしくはメールアドレスを入力して下さい。", HttpStatus.BAD_REQUEST),
    PASSWORD_IS_REQUIRED("パスワードを入力してください", HttpStatus.BAD_REQUEST),
    USER_NOT_VERIFIED("メールアドレス認証がされていないユーザーです。", HttpStatus.UNAUTHORIZED),
    USER_NAME_IS_REQUIRED("ユーザー名を入力して下さい。", HttpStatus.BAD_REQUEST),
    EMAIL_IS_REQUIRED("メールアドレスを入力してください。", HttpStatus.BAD_REQUEST),
    USER_NAME_LENGTH_INVALID("ユーザー名は4文字以上30文字以内で入力してください。", HttpStatus.BAD_REQUEST),
    PASSWORD_LENGTH_INVALID("パスワードは8文字以上20文字以内で入力してください。", HttpStatus.BAD_REQUEST),
    EMAIL_IS_INVALID("正しいメールアドレスを入力してください。", HttpStatus.BAD_REQUEST),
    USER_NAME_NOT_UNIQUE("すでに存在するユーザー名です。", HttpStatus.CONFLICT);

    private String message;
    private HttpStatus status;

    private YomouMessage(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

}
