package com.deepak.trading.service.impl;

import com.deepak.trading.dto.UserRequest;
import com.deepak.trading.entity.User;
import com.deepak.trading.exception.UserAlreadyExistsException;
import com.deepak.trading.repository.UserRepository;
import com.deepak.trading.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                    "User already exists with email : " + request.getEmail());
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }
}