package com.journeyjoy.service;

import com.journeyjoy.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    // Secret key for signing JWT (keep this secret & use environment variable or config in production)
    private static final String SECRET_KEY = "my-secret-key-12345";

    // Token validity time (e.g., 1 hour)
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
