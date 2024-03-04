package com.minkyu.realworld.common.error;

public class BaseCustomException extends RuntimeException {

    ErrorCode errorCode;

    public BaseCustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
