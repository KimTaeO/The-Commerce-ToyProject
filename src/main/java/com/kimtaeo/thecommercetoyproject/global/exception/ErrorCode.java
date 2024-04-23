package com.kimtaeo.thecommercetoyproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ;

    private final String message;
    private final Integer status;
}
