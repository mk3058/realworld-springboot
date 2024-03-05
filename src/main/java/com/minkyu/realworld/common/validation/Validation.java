package com.minkyu.realworld.common.validation;

import com.google.common.base.Preconditions;
import com.minkyu.realworld.user.domain.Role;
import java.util.regex.Pattern;

public class Validation {

    private static final String emailPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String urlPattern = "^((http|https)://)?(www.)?([a-zA-Z0-9]+)\\\\.[a-z]+([a-zA-z0-9.?#]+)?";

    public static void email(String email) {
        Preconditions.checkNotNull(email, "email must be provided");
        Preconditions.checkArgument(Pattern.compile(emailPattern).matcher(email).matches(),
            "invalid email format");
        Preconditions.checkArgument(email.length() <= 50, "email must be 50 characters or less");
    }

    public static void username(String username) {
        Preconditions.checkNotNull(username, "username must be provided");
        Preconditions.checkArgument(username.length() <= 10,
            "username must be 10 characters or less");
    }

    public static void password(String password) {
        Preconditions.checkNotNull(password, "password must be provided");
    }

    public static void role(Role role) {
        Preconditions.checkNotNull(role, "role must be provided");
        Preconditions.checkArgument(role.equals(Role.ROLE_USER), "Initial role cannot be admin");
    }

    public static void bio(String bio) {
        Preconditions.checkNotNull(bio, "bio must be provided");
        Preconditions.checkArgument(bio.length() <= 100, "bio must be 100 characters or less");
    }

    public static void url(String url) {
        Preconditions.checkNotNull(url, "url must be provided");
        Preconditions.checkArgument(Pattern.compile(urlPattern).matcher(url).matches(),
            "invalid url format");
    }

    public static void email(String email, boolean nullable) {
        if (nullable && email == null) {
            return;
        }
        Preconditions.checkNotNull(email, "email must be provided");
        Preconditions.checkArgument(Pattern.compile(emailPattern).matcher(email).matches(),
            "invalid email format");
        Preconditions.checkArgument(email.length() <= 50, "email must be 50 characters or less");
    }

    public static void username(String username, boolean nullable) {
        if (nullable && username == null) {
            return;
        }
        Preconditions.checkNotNull(username, "username must be provided");
        Preconditions.checkArgument(username.length() <= 10,
            "username must be 10 characters or less");
    }

    public static void password(String password, boolean nullable) {
        if (nullable && password == null) {
            return;
        }
        Preconditions.checkNotNull(password, "password must be provided");
    }

    public static void role(Role role, boolean nullable) {
        if (nullable && role == null) {
            return;
        }
        Preconditions.checkNotNull(role, "role must be provided");
        Preconditions.checkArgument(role.equals(Role.ROLE_USER), "Initial role cannot be admin");
    }

    public static void bio(String bio, boolean nullable) {
        if (nullable && bio == null) {
            return;
        }
        Preconditions.checkNotNull(bio, "bio must be provided");
        Preconditions.checkArgument(bio.length() <= 100, "bio must be 100 characters or less");
    }

    public static void url(String url, boolean nullable) {
        if (nullable && url == null) {
            return;
        }
        Preconditions.checkNotNull(url, "url must be provided");
        Preconditions.checkArgument(Pattern.compile(urlPattern).matcher(url).matches(),
            "invalid url format");
    }

    public static void title(String title) {
        Preconditions.checkNotNull(title, "title must be provided");
        Preconditions.checkArgument(title.length() <= 100,
            "title length must be less or equal to 100");
    }

    public static void slug(String slug) {
        Preconditions.checkNotNull(slug, "slug must be provided");
        Preconditions.checkArgument(slug.length() <= 100,
            "slug length must be less or equal to 100");
    }

    public static void body(String body) {
        Preconditions.checkNotNull(body, "body must be provided");
    }
}
