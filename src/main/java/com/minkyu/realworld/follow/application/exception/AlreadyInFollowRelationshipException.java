package com.minkyu.realworld.follow.application.exception;

import com.minkyu.realworld.common.error.BaseCustomException;
import com.minkyu.realworld.common.error.ErrorCode;

public class AlreadyInFollowRelationshipException extends BaseCustomException {

    public AlreadyInFollowRelationshipException() {
        super(ErrorCode.DUPLICATE_REQUEST);
    }
}
