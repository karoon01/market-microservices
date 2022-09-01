package com.yosypchuk.auth.controller;

import com.yosypchuk.auth.api.AuthApi;
import com.yosypchuk.auth.model.AuthRequest;
import com.yosypchuk.auth.model.RegisterRequest;
import com.yosypchuk.auth.model.User;
import com.yosypchuk.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public User login(AuthRequest authRequest) {
        return null;
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        return null;
    }
}
