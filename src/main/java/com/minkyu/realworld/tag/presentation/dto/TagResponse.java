package com.minkyu.realworld.tag.presentation.dto;

import com.google.common.base.Preconditions;
import java.util.List;

public record TagResponse(List<String> tags) {

    public TagResponse {
        Preconditions.checkNotNull(tags, "Tags must be provided");
    }
}
