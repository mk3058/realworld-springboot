package com.minkyu.realworld.follow.application;

import com.minkyu.realworld.auth.application.AuthService;
import com.minkyu.realworld.follow.application.exception.AlreadyInFollowRelationshipException;
import com.minkyu.realworld.follow.application.exception.NotInFollowRelationshipException;
import com.minkyu.realworld.follow.domain.Follow;
import com.minkyu.realworld.follow.domain.repository.FollowRepository;
import com.minkyu.realworld.jwt.CustomUserDetails;
import com.minkyu.realworld.user.application.exception.UserNotFoundException;
import com.minkyu.realworld.user.domain.User;
import com.minkyu.realworld.user.domain.repository.UserRepository;
import com.minkyu.realworld.user.presentation.dto.ProfileResponse;
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
    public ProfileResponse createByUsername(String followeeName) {
        CustomUserDetails userDetails = authService.findAuthenticatedUser();
        User follower = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(UserNotFoundException::new);
        User followee = userRepository.findByUsername(followeeName)
            .orElseThrow(UserNotFoundException::new);

        if (followRepository.existsByFollowerAndFollowee(follower, followee)) {
            throw new AlreadyInFollowRelationshipException();
        }
        Follow follow = new Follow(follower, followee);
        followRepository.save(follow);
        return ProfileResponse.fromEntity(followee, isFollower(follower, followee));
    }

    @Transactional
    public ProfileResponse deleteByUsername(String followeeName) {
        CustomUserDetails userDetails = authService.findAuthenticatedUser();
        User follower = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(UserNotFoundException::new);
        User followee = userRepository.findByUsername(followeeName)
            .orElseThrow(UserNotFoundException::new);

        if (!followRepository.existsByFollowerAndFollowee(follower, followee)) {
            throw new NotInFollowRelationshipException();
        }
        followRepository.deleteByFollowerAndFollowee(follower, followee);
        return ProfileResponse.fromEntity(followee, isFollower(follower, followee));
    }

    @Transactional(readOnly = true)
    public Boolean isFollower(User follower, User followee) {
        return followRepository.existsByFollowerAndFollowee(follower, followee);
    }

}
