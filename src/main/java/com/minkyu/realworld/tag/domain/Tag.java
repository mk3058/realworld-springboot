package com.minkyu.realworld.tag.domain;

import com.google.common.base.Preconditions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 40)
    private String name;

    public Tag(String name) {
        Preconditions.checkNotNull(name, "name must be provided");
        Preconditions.checkArgument(name.length() <= 40,
            "Tags must be 40 characters or less in length");

        this.name = name;
    }
}
