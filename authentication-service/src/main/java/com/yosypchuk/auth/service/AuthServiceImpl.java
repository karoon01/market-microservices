package com.yosypchuk.auth.service;

import com.yosypchuk.auth.client.UserFeignClient;
import com.yosypchuk.auth.model.AuthRequest;
import com.yosypchuk.auth.model.RegisterRequest;
import com.yosypchuk.auth.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final UserFeignClient userClient;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        log.info("Hash password for user with email: {}", email);
        registerRequest.setPassword(passwordEncoder.encode(password));

        log.info("Register user with email: {}", email);
        User user = userClient.createUser(registerRequest);

        return user;
    }

    @Override
    public User login(AuthRequest authRequest) {
        String email = authRequest.getEmail();

        log.info("Trying to get user with email: {}", email);
        User user = userClient.getUserByEmail(email);

        String jwtToken = jwtUtils.generateToken(user);

        return ;
    }
}
