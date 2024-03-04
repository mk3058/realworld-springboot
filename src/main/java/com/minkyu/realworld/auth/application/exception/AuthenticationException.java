package com.minkyu.realworld.auth.application.exception;

import com.minkyu.realworld.common.error.BaseCustomException;
import com.minkyu.realworld.common.error.ErrorCode;

public class AuthenticationException extends BaseCustomException {

    public AuthenticationException() {
        super(ErrorCode.UNAUTHENTICATED);
    }
}
