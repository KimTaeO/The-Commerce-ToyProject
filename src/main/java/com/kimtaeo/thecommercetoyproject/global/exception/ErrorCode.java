package com.kimtaeo.thecommercetoyproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다", 409);

    private final String message;
    private final Integer status;
}
