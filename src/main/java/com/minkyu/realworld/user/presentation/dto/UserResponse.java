package com.minkyu.realworld.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.minkyu.realworld.common.validation.Validation;
import com.minkyu.realworld.user.domain.User;

@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public record UserResponse(String email, String token, String username, String bio, String image) {

    public UserResponse {
        Validation.email(email);
        Validation.username(username);
        Validation.url(image, true);
        Validation.bio(bio, true);
    }

    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getEmail(), null, user.getUsername(), user.getBio(),
            user.getImage());
    }

}
