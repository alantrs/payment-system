package com.payment.paymentsystem.config.exceptions;

public record ErrorResponse(String msg, Integer statusCode) {
}
