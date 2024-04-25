package com.kimtaeo.thecommercetoyproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다", 404),
    ALREADY_EXIST_MEMBER_ID("이미 존재하는 아이디입니다", 409),
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다", 409),
    ALREADY_EXIST_PHONENUMBER("이미 존재하는 전화번호입니다", 409);

    private final String message;
    private final Integer status;
}
