package com.yosypchuk.gateway.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${security.jwt.secret}")
    private String secret;
}
