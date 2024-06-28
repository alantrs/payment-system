package com.payment.paymentsystem.config.exceptions;

public class ValidationException extends RuntimeException{

    private String msg;

    public ValidationException(String msg){
        this.msg = msg;
    }
}
