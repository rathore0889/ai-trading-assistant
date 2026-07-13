package com.deepak.trading.service;

import com.deepak.trading.dto.LoginRequest;
import com.deepak.trading.dto.LoginResponse;
import com.deepak.trading.dto.response.UserProfileResponse;
import com.deepak.trading.entity.User;
import com.deepak.trading.mapper.UserMapper;
import com.deepak.trading.repository.UserRepository;
import com.deepak.trading.security.JwtService;
import com.deepak.trading.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void shouldLoginSuccessfully() {

        LoginRequest request = new LoginRequest();

        request.setEmail("test@gmail.com");
        request.setPassword("password123");

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        when(jwtService.generateToken(request.getEmail()))
                .thenReturn("dummy-jwt-token");

        LoginResponse response = authService.login(request);

        assertNotNull(response);

        assertEquals("dummy-jwt-token", response.getToken());

        verify(authenticationManager)
                .authenticate(any());

        verify(jwtService)
                .generateToken(request.getEmail());
    }

    @Test
    void shouldReturnCurrentUser() {

        // Arrange
        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication())
                .thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName())
                .thenReturn("test@gmail.com");

        User user = new User();

        user.setEmail("test@gmail.com");

        user.setFullName("Deepak Rathore");

        user.setRole("USER");

        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        UserProfileResponse response = new UserProfileResponse();

        response.setEmail("test@gmail.com");

        response.setFullName("Deepak Rathore");

        when(userMapper.toProfile(user))
                .thenReturn(response);

        // Act
        UserProfileResponse result = authService.getCurrentUser();

        // Assert
        assertNotNull(result);

        assertEquals("test@gmail.com", result.getEmail());

        assertEquals("Deepak Rathore", result.getFullName());

        verify(userRepository).findByEmail("test@gmail.com");

        verify(userMapper).toProfile(user);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication())
                .thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName())
                .thenReturn("unknown@gmail.com");

        when(userRepository.findByEmail("unknown@gmail.com"))
                .thenReturn(Optional.empty());

        assertThrows(
                UsernameNotFoundException.class,
                () -> authService.getCurrentUser());

        verify(userRepository)
                .findByEmail("unknown@gmail.com");
    }
}