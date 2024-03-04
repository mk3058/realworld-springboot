package com.minkyu.realworld.user.presentation;

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
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserResponse profileResponse = userService.findCurrentUser();
        return ResponseEntity.ok(profileResponse);
    }

    @GetMapping("/profiles/{username}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable("username") String username) {
        ProfileResponse profileResponse = userService.findUserByUsername(username);
        return ResponseEntity.ok(profileResponse);
    }

    @PutMapping("/user")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequest dto) {
        UserResponse profileResponse = userService.updateCurrentUser(dto);
        return ResponseEntity.ok(profileResponse);
    }
}