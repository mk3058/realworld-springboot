package com.minkyu.realworld.auth.presentation;

import com.minkyu.realworld.auth.application.AuthService;
import com.minkyu.realworld.auth.presentation.dto.AuthResponse;
import com.minkyu.realworld.auth.presentation.dto.JoinRequest;
import com.minkyu.realworld.auth.presentation.dto.LoginRequest;
import com.minkyu.realworld.common.error.ErrorResponse;
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
    public ResponseEntity<?> join(@RequestBody JoinRequest dto) {
        try {
            AuthResponse response = authService.join(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body(ErrorResponse.fromMessage(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
        try {
            AuthResponse response = authService.login(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                .body(ErrorResponse.fromMessage(e.getMessage()));
        }
    }
}
