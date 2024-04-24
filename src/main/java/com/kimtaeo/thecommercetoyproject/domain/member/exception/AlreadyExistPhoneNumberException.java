package com.kimtaeo.thecommercetoyproject.domain.member.exception;

import com.kimtaeo.thecommercetoyproject.global.exception.BasicException;
import com.kimtaeo.thecommercetoyproject.global.exception.ErrorCode;

public class AlreadyExistPhoneNumberException extends BasicException {
    public AlreadyExistPhoneNumberException(String message) {
        super(message, ErrorCode.ALREADY_EXIST_PHONENUMBER.getStatus());
    }
}
