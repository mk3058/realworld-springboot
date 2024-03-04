package com.minkyu.realworld.user.application.exception;

import com.minkyu.realworld.common.error.BaseCustomException;
import com.minkyu.realworld.common.error.ErrorCode;

public class UserNotFoundException extends BaseCustomException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
