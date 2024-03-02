package com.minkyu.realworld.user.domain;

import com.google.common.base.Preconditions;
import com.minkyu.realworld.common.validation.Validation;
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
        Validation.username(username);
        Validation.email(email);
        Validation.password(password);
        Validation.url(image, true);
        Validation.bio(bio, true);
        Validation.role(role);

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
        Validation.username(username, true);
        Validation.email(email, true);
        Validation.password(password, true);
        Validation.url(image, true);
        Validation.bio(bio, true);
        Validation.role(role, true);
        Preconditions.checkArgument(
            email != null || username != null || password != null || image != null || bio != null,
            "At least one parameter must be given");

        this.username = (username != null) ? username : this.username;
        this.email = (email != null) ? email : this.email;
        this.password = (password != null) ? password : this.password;
        this.image = (image != null) ? image : this.image;
        this.bio = (bio != null) ? bio : this.bio;
        this.role = (role != null) ? role : this.role;

    }
}
