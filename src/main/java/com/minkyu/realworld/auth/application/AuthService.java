package com.minkyu.realworld.auth.application;

import com.minkyu.realworld.auth.application.exception.AuthenticationException;
import com.minkyu.realworld.auth.presentation.dto.JoinRequest;
import com.minkyu.realworld.auth.presentation.dto.LoginRequest;
import com.minkyu.realworld.jwt.CustomUserDetails;
import com.minkyu.realworld.jwt.JwtUtil;
import com.minkyu.realworld.user.application.exception.DuplicateUserException;
import com.minkyu.realworld.user.application.exception.UserNotFoundException;
import com.minkyu.realworld.user.domain.User;
import com.minkyu.realworld.user.domain.repository.UserRepository;
import com.minkyu.realworld.user.presentation.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Transactional
    public UserResponse join(JoinRequest dto) {
        String username = dto.username();
        String email = dto.email();
        String encodedPassword = bCryptPasswordEncoder.encode(dto.password());

        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserException();
        }

        User user = new User(username, email, encodedPassword);
        userRepository.save(user);
        return UserResponse.fromEntity(user);
    }

    @Transactional
    public UserResponse login(LoginRequest dto) {
        String email = dto.email();
        String password = dto.password();

        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.createJwt(user.getUsername(), user.getRole().name());
        return new UserResponse(user.getEmail(), token, user.getUsername(), user.getBio(),
            user.getImage());
    }

    @Transactional(readOnly = true)
    public CustomUserDetails findAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return (CustomUserDetails) principal;
        }
        throw new AuthenticationException();
    }
}
