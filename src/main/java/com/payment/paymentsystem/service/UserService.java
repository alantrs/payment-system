package com.payment.paymentsystem.service;

import com.payment.paymentsystem.config.exceptions.EmailExists;
import com.payment.paymentsystem.dto.UserRequest;
import com.payment.paymentsystem.dto.UserResponse;
import com.payment.paymentsystem.entity.User;
import com.payment.paymentsystem.repository.UserRepository;
import com.payment.paymentsystem.utils.RandomCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }



    public UserResponse createUser(UserRequest userRequest){
        if (userRepository.findByEmail(userRequest.email()) != null){
            throw new EmailExists("Email already exists");
        }

        int code = RandomCode.generateCode();

        User user = new User(userRequest.name(), userRequest.email(), passwordEncoder.encode(userRequest.password()));
        user.setVerificationCode(code);
        user.setEnable(false);

        User userSaved = userRepository.save(user);
        return new UserResponse(userSaved);
    }
}
