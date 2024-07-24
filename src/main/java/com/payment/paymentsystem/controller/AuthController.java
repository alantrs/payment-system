package com.payment.paymentsystem.controller;

import com.payment.paymentsystem.dto.LoginDTO;
import com.payment.paymentsystem.entity.User;
import com.payment.paymentsystem.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                dados.email(), dados.password()
        );
        var authManager = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) authManager.getPrincipal());

        return ResponseEntity.ok().body(token);
    }
}
