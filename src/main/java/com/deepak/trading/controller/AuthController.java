package com.deepak.trading.controller;

import com.deepak.trading.dto.LoginRequest;
import com.deepak.trading.dto.LoginResponse;
import com.deepak.trading.dto.UserRequest;
import com.deepak.trading.dto.response.UserProfileResponse;
import com.deepak.trading.entity.User;
import com.deepak.trading.service.AuthService;
import com.deepak.trading.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/register")
    public User register(
            @RequestBody UserRequest request) {

        return userService.createUser(request);
    }

    @GetMapping("/me")
    public UserProfileResponse currentUser() {

        return authService.getCurrentUser();
    }

}