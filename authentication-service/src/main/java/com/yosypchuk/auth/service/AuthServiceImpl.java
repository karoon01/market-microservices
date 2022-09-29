package com.yosypchuk.auth.service;

import com.yosypchuk.auth.model.AuthRequest;
import com.yosypchuk.auth.model.RegisterRequest;
import com.yosypchuk.auth.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    private final static String USER_SERVICE_URL = "http://localhost:8082/user/";

    @Override
    public User register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        log.info("Hash password for user with email: {}", email);
        registerRequest.setPassword(passwordEncoder.encode(password));

        log.info("Register user with email: {}", email);
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest);
        User user = restTemplate.postForObject(USER_SERVICE_URL, request, User.class);

        return user;
    }

    @Override
    public User login(AuthRequest authRequest) {
        String email = authRequest.getEmail();

        log.info("Trying to get user with email: {}", email);
        User user = restTemplate.getForObject(USER_SERVICE_URL + email, User.class);

        String jwtToken = jwtUtils.generateToken(user);

        return user;
    }
}
