package com.minkyu.realworld.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.minkyu.realworld.user.domain.User;

@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public record ProfileResponse(String username, String bio, String image, Boolean following) {

    public static ProfileResponse fromEntity(User target, Boolean following) {
        return new ProfileResponse(target.getUsername(), target.getBio(), target.getImage(),
            following);
    }
}
