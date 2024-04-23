package com.kimtaeo.thecommercetoyproject.global.exception;

import lombok.Getter;

@Getter
public class BasicException extends RuntimeException {
    private final Integer status;
    public BasicException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}