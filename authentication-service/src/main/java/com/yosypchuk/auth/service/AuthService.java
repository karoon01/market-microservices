package com.yosypchuk.auth.service;

import com.yosypchuk.auth.model.AuthRequest;
import com.yosypchuk.auth.model.RegisterRequest;
import com.yosypchuk.auth.model.User;

public interface AuthService {
    User register(RegisterRequest registerRequest);

    User login(AuthRequest authRequest);
}
