package com.minkyu.realworld.follow.presentation;

import com.minkyu.realworld.common.error.ErrorResponse;
import com.minkyu.realworld.follow.application.FollowService;
import com.minkyu.realworld.user.presentation.dto.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{username}/follow")
    public ResponseEntity<?> followUser(@PathVariable("username") String username) {
        try {
            ProfileResponse profileResponse = followService.createByUsername(username);
            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body(ErrorResponse.fromMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/{username}/follow")
    public ResponseEntity<?> unfollowUser(@PathVariable("username") String username) {
        try {
            ProfileResponse profileResponse = followService.deleteByUsername(username);
            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body(ErrorResponse.fromMessage(e.getMessage()));
        }
    }
}
