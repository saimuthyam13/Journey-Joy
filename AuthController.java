package com.journeyjoy.controller;

import com.journeyjoy.model.User;
import com.journeyjoy.service.UserService;
import com.journeyjoy.service.JwtService;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.journeyjoy.model.AuthResponse; // or wherever you put it


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 1st encoding
        User savedUser = userService.saveUser(user);                  // userService.saveUser again encodes:
        // Inside userService.saveUser:
        // user.setPassword(passwordEncoder.encode(user.getPassword())); // 2nd encoding!
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String rawPassword = loginRequest.get("password");

        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("Raw password: " + rawPassword);
            System.out.println("Stored hashed password: " + user.getPassword());
            boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
            System.out.println("Matches: " + matches);

            if (matches) {
                String token = jwtService.generateToken(user);
                return ResponseEntity.ok(new AuthResponse(token));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello, AuthController is working!";
    }
}
