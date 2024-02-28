package com.minkyu.realworld.user.domain;

import com.google.common.base.Preconditions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 10, unique = true, nullable = false)
    private String username;

    private String image;

    @Column(length = 100)
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Role role;

    public User(String username, String email, String password) {
        this(null, username, email, password, null, null, Role.ROLE_USER);
    }

    private User(Long id, String username, String email, String password, String image, String bio,
        Role role) {
        Preconditions.checkNotNull(username, "username must be provided");
        Preconditions.checkNotNull(email, "email must be provided");
        Preconditions.checkNotNull(password, "password must be provided");
        Preconditions.checkNotNull(role, "role must be provided");
        Preconditions.checkArgument(username.length() <= 10,
            "username must be 10 characters or less");
        Preconditions.checkArgument(email.length() <= 50, "email must be 50 characters or less");
        Preconditions.checkArgument(role.equals(Role.ROLE_USER), "Initial role cannot be admin");
        if (bio != null) {
            Preconditions.checkArgument(bio.length() <= 100, "bio must be 100 characters or less");
        }

        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.image = image;
        this.bio = bio;
        this.role = role;
    }

    public void update(String username, String email, String password, String image, String bio,
        Role role) {
        Preconditions.checkNotNull(username, "username must be provided");
        Preconditions.checkNotNull(email, "email must be provided");
        Preconditions.checkNotNull(password, "password must be provided");
        Preconditions.checkNotNull(role, "role must be provided");
        Preconditions.checkArgument(username.length() <= 10,
            "username must be 10 characters or less");
        Preconditions.checkArgument(email.length() <= 50, "email must be 50 characters or less");
        if (bio != null) {
            Preconditions.checkArgument(bio.length() <= 100, "bio must be 100 characters or less");
        }

        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.bio = bio;
        this.role = role;
    }
}
