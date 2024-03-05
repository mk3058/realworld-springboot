package com.minkyu.realworld.tag.presentation;

import com.minkyu.realworld.tag.application.TagService;
import com.minkyu.realworld.tag.presentation.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping("")
    public ResponseEntity<TagResponse> getTags() {
        return ResponseEntity.ok(tagService.finaAll());
    }
}
