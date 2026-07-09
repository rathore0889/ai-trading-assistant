package com.deepak.trading.service;

import com.deepak.trading.dto.UserRequest;
import com.deepak.trading.entity.User;

public interface UserService {

    User createUser(UserRequest request);
}
