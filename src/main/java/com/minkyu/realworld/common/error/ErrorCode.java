package com.minkyu.realworld.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATE_USER(HttpStatus.UNPROCESSABLE_ENTITY, "User already exists"),
    USER_NOT_FOUND(HttpStatus.UNPROCESSABLE_ENTITY, "User not found");

    private final HttpStatus status;
    private final String message;
}
