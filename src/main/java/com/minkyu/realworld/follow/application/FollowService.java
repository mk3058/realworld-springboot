package com.minkyu.realworld.follow.application;

import com.minkyu.realworld.follow.domain.repository.FollowRepository;
import com.minkyu.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public Boolean isFollower(User follower, User followee) throws Exception {
        return followRepository.existsByFollowerIdAndFolloweeId(follower.getId(), followee.getId());
    }

}
