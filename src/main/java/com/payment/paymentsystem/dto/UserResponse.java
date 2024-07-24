package com.payment.paymentsystem.dto;

import com.payment.paymentsystem.entity.User;

public record UserResponse(Long id, String name, String email) {

    public UserResponse(User user){
        this(user.getId(),
                user.getName(),
                user.getEmail());
    }
}
