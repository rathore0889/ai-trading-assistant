package com.deepak.trading.controller;

import com.deepak.trading.dto.LoginRequest;
import com.deepak.trading.dto.LoginResponse;
import com.deepak.trading.dto.UserRequest;
import com.deepak.trading.dto.response.UserProfileResponse;
import com.deepak.trading.entity.User;
import com.deepak.trading.service.AuthService;
import com.deepak.trading.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication",
        description = "User Registration and Login APIs"
)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Operation(
            summary = "Login User",
            description = "Authenticate user and generate JWT token"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login Successful"),
            @ApiResponse(responseCode = "401", description = "Invalid Credentials")
    })
    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @Operation(
            summary = "Register User",
            description = "Create a new user account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User Created"),
            @ApiResponse(responseCode = "409", description = "Email Already Exists"),
            @ApiResponse(responseCode = "400", description = "Validation Failed")
    })
    @PostMapping("/register")
    public User register(
            @Valid @RequestBody UserRequest request) {

        return userService.createUser(request);
    }

    @GetMapping("/me")
    public UserProfileResponse currentUser() {

        return authService.getCurrentUser();
    }

}