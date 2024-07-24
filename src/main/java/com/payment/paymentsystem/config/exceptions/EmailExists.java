package com.payment.paymentsystem.config.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailExists extends RuntimeException {

    private String msg;

    public EmailExists(String msg){
        this.msg = msg;
    }

}
