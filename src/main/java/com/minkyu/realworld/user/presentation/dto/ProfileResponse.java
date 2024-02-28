package com.minkyu.realworld.user.presentation.dto;

import com.minkyu.realworld.user.domain.User;
import java.util.UUID;

public record ProfileResponse(String username, String bio, UUID image, Boolean following) {

    public static ProfileResponse fromEntity(User target, Boolean following) {
        return new ProfileResponse(target.getUsername(), target.getBio(), target.getImage(),
            following);
    }
}
