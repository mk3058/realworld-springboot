package com.minkyu.realworld.user.application;

import com.minkyu.realworld.auth.application.AuthService;
import com.minkyu.realworld.common.exception.UserNotFoundException;
import com.minkyu.realworld.follow.application.FollowService;
import com.minkyu.realworld.follow.domain.repository.FollowRepository;
import com.minkyu.realworld.jwt.CustomUserDetails;
import com.minkyu.realworld.user.domain.User;
import com.minkyu.realworld.user.domain.repository.UserRepository;
import com.minkyu.realworld.user.presentation.dto.ProfileResponse;
import com.minkyu.realworld.user.presentation.dto.UserResponse;
import com.minkyu.realworld.user.presentation.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthService authService;
    private final FollowService followService;

    @Transactional(readOnly = true)
    public UserResponse findCurrentUser() throws Exception {
        CustomUserDetails userDetails = authService.findAuthenticatedUser();
        User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UserNotFoundException("Cannot find current user!"));

        return UserResponse.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public ProfileResponse findUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("Cannot find user " + username));

        CustomUserDetails userDetails;
        try {
            userDetails = authService.findAuthenticatedUser();
        } catch (Exception e) {
            return ProfileResponse.fromEntity(user, false);
        }
        User current = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UserNotFoundException("Cannot find current user!"));
        return ProfileResponse.fromEntity(user, followService.isFollower(current, user));
    }

    @Transactional
    public UserResponse updateCurrentUser(UserUpdateRequest dto) throws Exception {
        CustomUserDetails userDetails = authService.findAuthenticatedUser();
        User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UserNotFoundException("Cannot find current user!"));
        String requestedPassword = dto.password();

        if (requestedPassword != null) {
            requestedPassword = bCryptPasswordEncoder.encode(dto.password());
        }
        user.update(dto.username(), dto.email(), requestedPassword, dto.image(), dto.bio(), null);
        return UserResponse.fromEntity(user);
    }
}
