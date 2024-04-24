package com.kimtaeo.thecommercetoyproject.domain.member.exception;

import com.kimtaeo.thecommercetoyproject.global.exception.BasicException;
import com.kimtaeo.thecommercetoyproject.global.exception.ErrorCode;

public class AlreadyExistMemberIdException extends BasicException {
    public AlreadyExistMemberIdException(String message) {
        super(message, ErrorCode.ALREADY_EXIST_MEMBER_ID.getStatus());
    }
}
