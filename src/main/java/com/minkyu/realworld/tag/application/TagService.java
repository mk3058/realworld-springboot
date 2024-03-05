package com.minkyu.realworld.tag.application;

import com.minkyu.realworld.tag.application.exception.TagNotFoundException;
import com.minkyu.realworld.tag.domain.Tag;
import com.minkyu.realworld.tag.domain.repository.TagRepository;
import com.minkyu.realworld.tag.presentation.dto.TagResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public TagResponse finaAll() {
        List<Tag> tags = tagRepository.findAll();

        return new TagResponse(tags.stream()
            .map(Tag::getName)
            .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public TagResponse findByName(String name) {
        Tag tag = tagRepository.findTagByName(name).orElseThrow(TagNotFoundException::new);
        List<String> tags = new ArrayList<>();

        tags.add(tag.getName());
        return new TagResponse(tags);
    }

    @Transactional
    public TagResponse save(String name) {
        Tag tag = new Tag(name);
        List<String> tags = new ArrayList<>();

        tagRepository.save(tag);
        tags.add(tag.getName());
        return new TagResponse(tags);
    }
}
