package com.minkyu.realworld.common.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BaseCustomException.class)
    private ResponseEntity<ErrorResponse> defaultCustomExceptionHandler(BaseCustomException e) {
        return ResponseEntity.unprocessableEntity().body(ErrorResponse.fromMessage(e.getMessage()));
    }
}
