package com.deepak.trading.service;

import com.deepak.trading.dto.LoginRequest;
import com.deepak.trading.dto.LoginResponse;
import com.deepak.trading.dto.response.UserProfileResponse;
import com.deepak.trading.entity.User;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    UserProfileResponse getCurrentUser();

}
