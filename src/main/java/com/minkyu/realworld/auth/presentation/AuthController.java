package com.minkyu.realworld.auth.presentation;

import com.minkyu.realworld.auth.application.AuthService;
import com.minkyu.realworld.auth.presentation.dto.JoinRequest;
import com.minkyu.realworld.auth.presentation.dto.LoginRequest;
import com.minkyu.realworld.user.presentation.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("")
    public ResponseEntity<UserResponse> join(@RequestBody JoinRequest dto) {
        UserResponse response = authService.join(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest dto) {
        UserResponse response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}
