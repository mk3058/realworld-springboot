package com.minkyu.realworld.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATE_USER(HttpStatus.UNPROCESSABLE_ENTITY, "User already exists"),
    DUPLICATE_REQUEST(HttpStatus.UNPROCESSABLE_ENTITY, "Duplicate request"),
    INVALID_REQUEST(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized request"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "User not authenticated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Permission denied");

    private final HttpStatus status;
    private final String message;
}
