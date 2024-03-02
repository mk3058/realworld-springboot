package com.minkyu.realworld.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.common.base.Preconditions;
import com.minkyu.realworld.common.validation.Validation;

@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public record UserUpdateRequest(String email, String username, String password, String image,
                                String bio) {

    public UserUpdateRequest {
        Validation.username(username, true);
        Validation.email(email, true);
        Validation.password(password, true);
        Validation.url(image, true);
        Validation.bio(bio, true);
        Preconditions.checkArgument(
            email != null || username != null || password != null || image != null || bio != null,
            "At least one parameter must be given");
    }
}
