package com.minkyu.realworld.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.common.base.Preconditions;
import com.minkyu.realworld.common.validation.Validation;
import com.minkyu.realworld.user.domain.User;

@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public record ProfileResponse(String username, String bio, String image, Boolean following) {

    public ProfileResponse {
        Validation.username(username);
        Preconditions.checkNotNull(following, "following must be provided");
    }

    public static ProfileResponse fromEntity(User target, Boolean following) {
        return new ProfileResponse(target.getUsername(), target.getBio(), target.getImage(),
            following);
    }
}
