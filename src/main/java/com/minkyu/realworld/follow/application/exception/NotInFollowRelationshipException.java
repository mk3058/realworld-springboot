package com.minkyu.realworld.follow.application.exception;

import com.minkyu.realworld.common.error.BaseCustomException;
import com.minkyu.realworld.common.error.ErrorCode;

public class NotInFollowRelationshipException extends BaseCustomException {

    public NotInFollowRelationshipException() {
        super(ErrorCode.INVALID_REQUEST);
    }
}
