package com.minkyu.realworld.auth.presentation.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.minkyu.realworld.user.domain.User;
import java.util.UUID;

@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public record AuthResponse(String email, String token, String username, String bio, UUID image) {

    public static AuthResponse fromUser(User user) {
        return new AuthResponse(user.getEmail(), null, user.getUsername(), user.getBio(),
            user.getImage());
    }

}
