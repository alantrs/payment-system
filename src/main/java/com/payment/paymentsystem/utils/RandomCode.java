package com.payment.paymentsystem.utils;

import java.util.Random;

public class RandomCode {

    private static final int DIGITS_CODE = 6;

    public static int generateCode() {
        Random random = new Random();
        int limit = (int) Math.pow(10, DIGITS_CODE) - 1;
        return random.nextInt(limit);
    }

}
