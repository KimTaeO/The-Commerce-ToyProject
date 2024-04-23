package com.kimtaeo.thecommercetoyproject.domain.member.exception;

import com.kimtaeo.thecommercetoyproject.global.exception.BasicException;
import com.kimtaeo.thecommercetoyproject.global.exception.ErrorCode;

public class AlreadyExistEmailException extends BasicException {
    public AlreadyExistEmailException(String message) {
        super(message, ErrorCode.ALREADY_EXIST_EMAIL.getStatus());
    }
}
