package com.kimtaeo.thecommercetoyproject.domain.member.exception;

import com.kimtaeo.thecommercetoyproject.global.exception.BasicException;
import com.kimtaeo.thecommercetoyproject.global.exception.ErrorCode;

public class MemberNotFoundException extends BasicException {
    public MemberNotFoundException(String message) {
        super(message, ErrorCode.MEMBER_NOT_FOUND.getStatus());
    }
}
