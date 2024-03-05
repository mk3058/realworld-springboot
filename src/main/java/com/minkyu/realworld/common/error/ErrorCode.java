package com.minkyu.realworld.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATE_USER(HttpStatus.UNPROCESSABLE_ENTITY, "User already exists"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "User not authenticated"),
    DUPLICATE_REQUEST(HttpStatus.UNPROCESSABLE_ENTITY, "Duplicate request"),
    INVALID_REQUEST(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized request"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Permission denied"),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Article not found"),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "Tag not found");

    private final HttpStatus status;
    private final String message;
}
