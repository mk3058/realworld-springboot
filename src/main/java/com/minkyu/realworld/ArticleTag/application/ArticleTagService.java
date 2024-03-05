package com.minkyu.realworld.ArticleTag.application;

import com.minkyu.realworld.ArticleTag.application.exception.TagAlreadyIncludedException;
import com.minkyu.realworld.ArticleTag.application.exception.TagNotIncludedException;
import com.minkyu.realworld.ArticleTag.domain.ArticleTag;
import com.minkyu.realworld.ArticleTag.domain.repository.ArticleTagRepository;
import com.minkyu.realworld.article.domain.Article;
import com.minkyu.realworld.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleTagService {

    private final ArticleTagRepository articleTagRepository;

    @Transactional
    public void createByArticleAndTag(Article article, Tag tag) {
        if (articleTagRepository.existsByArticleAndTag(article, tag)) {
            throw new TagAlreadyIncludedException();
        }

        ArticleTag articleTag = new ArticleTag(article, tag);
        articleTagRepository.save(articleTag);
    }

    @Transactional
    public void deleteByArticleAndTag(Article article, Tag tag) {
        if (!articleTagRepository.existsByArticleAndTag(article, tag)) {
            throw new TagNotIncludedException();
        }

        articleTagRepository.deleteByArticleAndTag(article, tag);
    }
}
