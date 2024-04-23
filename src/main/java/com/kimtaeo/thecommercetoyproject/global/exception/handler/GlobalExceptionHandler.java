package com.kimtaeo.thecommercetoyproject.global.exception.handler;

import com.kimtaeo.thecommercetoyproject.global.exception.BasicException;
import com.kimtaeo.thecommercetoyproject.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BasicException.class)
    public ResponseEntity<ErrorResponse> basicExceptionHandler(BasicException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getStatus()));
    }
}
