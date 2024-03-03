package com.minkyu.realworld.follow.application;

import com.minkyu.realworld.auth.application.AuthService;
import com.minkyu.realworld.common.exception.UserNotFoundException;
import com.minkyu.realworld.follow.domain.Follow;
import com.minkyu.realworld.follow.domain.repository.FollowRepository;
import com.minkyu.realworld.jwt.CustomUserDetails;
import com.minkyu.realworld.user.domain.User;
import com.minkyu.realworld.user.domain.repository.UserRepository;
import com.minkyu.realworld.user.presentation.dto.ProfileResponse;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Transactional
    public ProfileResponse createByUsername(String followeeName) throws Exception {
        CustomUserDetails userDetails = authService.findAuthenticatedUser();
        User follower = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UserNotFoundException("Cannot find current user!"));
        User followee = userRepository.findByUsername(followeeName)
            .orElseThrow(() -> new UserNotFoundException("Cannot find user named" + followeeName));

        if (followRepository.existsByFollowerAndFollowee(follower, followee)) {
            throw new DuplicateRequestException(follower.getUsername() + " is already following  " +
                followee.getUsername());
        }
        Follow follow = new Follow(follower, followee);
        followRepository.save(follow);
        return ProfileResponse.fromEntity(followee, isFollower(follower, followee));
    }

    @Transactional
    public ProfileResponse deleteByUsername(String followeeName) throws Exception {
        CustomUserDetails userDetails = authService.findAuthenticatedUser();
        User follower = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new UserNotFoundException("Cannot find current user!"));
        User followee = userRepository.findByUsername(followeeName)
            .orElseThrow(() -> new UserNotFoundException("Cannot find user named" + followeeName));

        if (!followRepository.existsByFollowerAndFollowee(follower, followee)) {
            throw new IllegalArgumentException(follower.getUsername() + " is not a follower of " +
                followee.getUsername());
        }
        followRepository.deleteByFollowerAndFollowee(follower, followee);
        return ProfileResponse.fromEntity(followee, isFollower(follower, followee));
    }

    @Transactional(readOnly = true)
    public Boolean isFollower(User follower, User followee) throws Exception {
        return followRepository.existsByFollowerAndFollowee(follower, followee);
    }

}
