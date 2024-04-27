package com.payment.paymentsystem.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EmailExists.class)
    public ResponseEntity<ErrorResponse> handleEmailExistsException(EmailExists exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMsg(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
