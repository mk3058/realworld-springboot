package com.minkyu.realworld.tag.application.exception;

import com.minkyu.realworld.common.error.BaseCustomException;
import com.minkyu.realworld.common.error.ErrorCode;

public class TagNotFoundException extends BaseCustomException {


    public TagNotFoundException() {
        super(ErrorCode.TAG_NOT_FOUND);
    }
}
