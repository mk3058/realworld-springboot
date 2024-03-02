package com.minkyu.realworld.auth.presentation.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.minkyu.realworld.common.validation.Validation;

@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public record LoginRequest(String email, String password) {

    public LoginRequest {
        Validation.email(email);
        Validation.password(password);
    }
}
