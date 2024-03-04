package com.minkyu.realworld.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATE_USER(HttpStatus.UNPROCESSABLE_ENTITY, "User already exists"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized request"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Permission denied");

    private final HttpStatus status;
    private final String message;
}
