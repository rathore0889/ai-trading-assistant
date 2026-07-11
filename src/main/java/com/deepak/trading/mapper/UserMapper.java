package com.deepak.trading.mapper;

import com.deepak.trading.dto.response.UserProfileResponse;
import com.deepak.trading.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileResponse toProfile(User user) {

        if (user == null) {
            return null;
        }

        return new UserProfileResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }
}