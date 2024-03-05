package com.minkyu.realworld.ArticleTag.domain;

import com.google.common.base.Preconditions;
import com.minkyu.realworld.article.domain.Article;
import com.minkyu.realworld.tag.domain.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "article_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @JoinColumn(name = "tag_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    public ArticleTag(Article article, Tag tag) {
        Preconditions.checkNotNull(article, "Article must be provided");
        Preconditions.checkNotNull(tag, "Tag must be provided");

        this.article = article;
        this.tag = tag;
    }
}
