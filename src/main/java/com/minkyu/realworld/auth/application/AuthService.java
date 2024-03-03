package com.minkyu.realworld.auth.application;

import com.minkyu.realworld.auth.presentation.dto.AuthResponse;
import com.minkyu.realworld.auth.presentation.dto.JoinRequest;
import com.minkyu.realworld.auth.presentation.dto.LoginRequest;
import com.minkyu.realworld.common.exception.UserAlreadyExistsException;
import com.minkyu.realworld.common.exception.UserNotFoundException;
import com.minkyu.realworld.jwt.CustomUserDetails;
import com.minkyu.realworld.jwt.JwtUtil;
import com.minkyu.realworld.user.domain.User;
import com.minkyu.realworld.user.domain.repository.UserRepository;
import jakarta.security.auth.message.AuthException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
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
    public AuthResponse join(JoinRequest dto) throws Exception {
        String username = dto.username();
        String email = dto.email();
        String encodedPassword = bCryptPasswordEncoder.encode(dto.password());

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User Already Exists!");
        }

        User user = new User(username, email, encodedPassword);
        userRepository.save(user);
        return AuthResponse.fromUser(user);
    }

    @Transactional
    public AuthResponse login(LoginRequest dto) throws Exception {
        String email = dto.email();
        String password = dto.password();

        User user = userRepository.findByEmail(email).orElseThrow(
            () -> new UserNotFoundException("User not found with email: " + email));
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.createJwt(user.getUsername(), user.getRole().name());
        return new AuthResponse(user.getEmail(), token, user.getUsername(), user.getBio(),
            user.getImage());
    }

    @Transactional(readOnly = true)
    public CustomUserDetails findAuthenticatedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthException("User not authenticated!");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return (CustomUserDetails) principal;
        }
        throw new UserPrincipalNotFoundException("Cannot find UserDetails!");
    }
}
