package com.minkyu.realworld.common.error;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

@JsonTypeName("errors")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public record ErrorResponse(List<String> body) {

    public static ErrorResponse FromMessages(List<String> messages) {
        return new ErrorResponse(messages);
    }
}
