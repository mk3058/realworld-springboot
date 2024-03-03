package com.minkyu.realworld.follow.domain.repository;

import com.minkyu.realworld.follow.domain.Follow;
import com.minkyu.realworld.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    void deleteByFollowerAndFollowee(User follower, User followee);

    Boolean existsByFollowerAndFollowee(User follower, User followee);
}
