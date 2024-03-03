package com.minkyu.realworld.user.application;

import com.minkyu.realworld.common.exception.UserNotFoundException;
import com.minkyu.realworld.follow.domain.repository.FollowRepository;
import com.minkyu.realworld.jwt.CustomUserDetails;
import com.minkyu.realworld.user.domain.User;
import com.minkyu.realworld.user.domain.repository.UserRepository;
import com.minkyu.realworld.user.presentation.dto.ProfileResponse;
import com.minkyu.realworld.user.presentation.dto.UserUpdateRequest;
import jakarta.security.auth.message.AuthException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ProfileResponse findCurrentUser() throws Exception {
        CustomUserDetails userDetails = findAuthenticatedUser();
        User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UserNotFoundException("Cannot find current user!"));

        return ProfileResponse.fromEntity(user, false);
    }

    public ProfileResponse findUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("Cannot find user " + username));

        return ProfileResponse.fromEntity(user, isFollower(user));
    }

    public ProfileResponse updateCurrentUser(UserUpdateRequest dto) throws Exception {
        CustomUserDetails userDetails = findAuthenticatedUser();
        User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UserNotFoundException("Cannot find current user!"));
        String requestedPassword = dto.password();

        if (requestedPassword != null) {
            requestedPassword = bCryptPasswordEncoder.encode(dto.password());
        }
        user.update(dto.username(), dto.email(), requestedPassword, dto.image(), dto.bio(), null);
        return ProfileResponse.fromEntity(user, false);
    }
    
    private Boolean isFollower(User target) throws Exception {
        CustomUserDetails userDetails = findAuthenticatedUser();
        User current = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
            NotFoundException::new);

        return followRepository.existsByFollowerIdAndFolloweeId(current.getId(), target.getId());
    }

    private CustomUserDetails findAuthenticatedUser() throws Exception {
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