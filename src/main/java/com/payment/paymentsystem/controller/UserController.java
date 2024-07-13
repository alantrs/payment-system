package com.payment.paymentsystem.controller;

import com.payment.paymentsystem.dto.UserRequest;
import com.payment.paymentsystem.dto.UserResponse;
import com.payment.paymentsystem.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user) throws MessagingException, UnsupportedEncodingException {
        UserResponse userSaved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @GetMapping("verify")
    public ResponseEntity<String> verify(@RequestParam Integer code){
        if (userService.verify(code)){
            return ResponseEntity.status(HttpStatus.OK).body("User verified");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not verified");
    }
}
