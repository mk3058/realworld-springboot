package com.minkyu.realworld.user.application.exception;

import com.minkyu.realworld.common.error.BaseCustomException;
import com.minkyu.realworld.common.error.ErrorCode;

public class DuplicateUserException extends BaseCustomException {

    public DuplicateUserException() {
        super(ErrorCode.DUPLICATE_USER);
    }
}
