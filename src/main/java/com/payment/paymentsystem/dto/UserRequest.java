package com.payment.paymentsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Name cannot be null")
        String name,
        @NotBlank(message = "Email cannot be null")
        @Email
        String email,
        @NotBlank(message = "Password cannot be null")
        @Size(min = 8, message = "The password must contain at least 8 characters.")
        String password) {
}
