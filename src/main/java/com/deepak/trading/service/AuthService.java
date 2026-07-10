package com.deepak.trading.service;

import com.deepak.trading.dto.LoginRequest;
import com.deepak.trading.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}
