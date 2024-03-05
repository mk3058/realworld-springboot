package com.minkyu.realworld.ArticleTag.application.exception;

import com.minkyu.realworld.common.error.BaseCustomException;
import com.minkyu.realworld.common.error.ErrorCode;

public class TagAlreadyIncludedException extends BaseCustomException {

    public TagAlreadyIncludedException() {
        super(ErrorCode.TAG_ALREADY_INCLUDED);
    }
}
