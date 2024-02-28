package com.minkyu.realworld.follow.domain.repository;

import com.minkyu.realworld.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Boolean existsByFollowerIdAndFolloweeId(Long follower, Long Followee);
}
