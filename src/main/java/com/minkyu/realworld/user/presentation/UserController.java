package com.minkyu.realworld.user.presentation;

import com.minkyu.realworld.common.error.ErrorResponse;
import com.minkyu.realworld.user.application.UserService;
import com.minkyu.realworld.user.presentation.dto.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser() {
        try {
            ProfileResponse profileResponse = userService.findCurrentUser();
            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body(ErrorResponse.fromMessage(e.getMessage()));
        }
    }

    @GetMapping("/profiles/{username}")
    public ResponseEntity<?> getProfile(@PathVariable("username") String username) {
        try {
            ProfileResponse profileResponse = userService.findUserByUsername(username);
            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body(ErrorResponse.fromMessage(e.getMessage()));
        }
    }
}