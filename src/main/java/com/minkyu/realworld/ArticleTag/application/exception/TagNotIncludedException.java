package com.minkyu.realworld.ArticleTag.application.exception;

import com.minkyu.realworld.common.error.BaseCustomException;
import com.minkyu.realworld.common.error.ErrorCode;

public class TagNotIncludedException extends BaseCustomException {

    public TagNotIncludedException() {
        super(ErrorCode.TAG_NOT_INCLUDED);
    }
}
