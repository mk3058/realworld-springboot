package com.minkyu.realworld.follow.domain;

import com.google.common.base.Preconditions;
import com.minkyu.realworld.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followee_id")
    private User followee;

    public Follow(User follower, User followee) {
        Preconditions.checkNotNull(follower, "follower must be provided");
        Preconditions.checkNotNull(followee, "followee must be provided");
        Preconditions.checkArgument(!follower.getId().equals(followee.getId()),
            "follower and followee cannot be same");
        
        this.follower = follower;
        this.followee = followee;
    }
}
