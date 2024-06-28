package com.payment.paymentsystem.controller;

import com.payment.paymentsystem.dto.UserRequest;
import com.payment.paymentsystem.dto.UserResponse;
import com.payment.paymentsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user) {
        UserResponse userSaved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }
}
