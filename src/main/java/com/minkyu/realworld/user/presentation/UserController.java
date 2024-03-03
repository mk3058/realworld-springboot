package com.minkyu.realworld.user.presentation;

import com.minkyu.realworld.common.error.ErrorResponse;
import com.minkyu.realworld.user.application.UserService;
import com.minkyu.realworld.user.presentation.dto.ProfileResponse;
import com.minkyu.realworld.user.presentation.dto.UserResponse;
import com.minkyu.realworld.user.presentation.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser() {
        try {
            UserResponse profileResponse = userService.findCurrentUser();
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

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest dto) {
        try {
            UserResponse profileResponse = userService.updateCurrentUser(dto);
            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body(ErrorResponse.fromMessage(e.getMessage()));
        }
    }
}