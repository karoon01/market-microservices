package com.yosypchuk.auth.service;

import com.yosypchuk.auth.model.AuthRequest;
import com.yosypchuk.auth.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    @Value("{jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expirationTime;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(User user) {
        String username = user.getEmail();

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("authorities", user.getRole());

        Date createdDate = new Date();
        Date expirationDate = java.sql.Date.valueOf(createdDate.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Jws<Claims> getAllFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
