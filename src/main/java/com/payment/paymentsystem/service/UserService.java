package com.payment.paymentsystem.service;

import com.payment.paymentsystem.config.exceptions.EmailExists;
import com.payment.paymentsystem.dto.UserRequest;
import com.payment.paymentsystem.dto.UserResponse;
import com.payment.paymentsystem.entity.User;
import com.payment.paymentsystem.entity.UserRole;
import com.payment.paymentsystem.repository.UserRepository;
import com.payment.paymentsystem.utils.RandomCode;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public UserService(UserRepository userRepository, MailService mailService){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.mailService = mailService;
    }



    public UserResponse createUser(UserRequest userRequest) throws MessagingException, UnsupportedEncodingException {
        if (userRepository.findByEmail(userRequest.email()) != null){
            throw new EmailExists("Email already exists");
        }

        int code = RandomCode.generateCode();

        User user = new User(userRequest, passwordEncoder.encode(userRequest.password()));
        user.setVerificationCode(code);

        User userSaved = userRepository.save(user);
        mailService.sendEmail(user);
        return new UserResponse(userSaved);
    }

    public Boolean verify(Integer verificationCode){
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.getEnable()){
            return false;
        }
        user.setVerificationCode(null);
        user.setEnable(true);
        user.setRole(UserRole.USER);
        userRepository.save(user);
        return true;
    }
}
