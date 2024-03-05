package com.minkyu.realworld.ArticleTag.domain.repository;

import com.minkyu.realworld.ArticleTag.domain.ArticleTag;
import com.minkyu.realworld.article.domain.Article;
import com.minkyu.realworld.tag.domain.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {

    public List<ArticleTag> findAllByArticle(Article article);

    public List<ArticleTag> findAllByTag(Tag tag);

    public void deleteByArticleAndTag(Article article, Tag tag);

    public Boolean existsByArticleAndTag(Article article, Tag tag);
}
