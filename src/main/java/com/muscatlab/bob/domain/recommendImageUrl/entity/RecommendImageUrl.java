package com.muscatlab.bob.domain.recommendImageUrl.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "recommend_image_url")
@Table(schema = "bob", name = "recommend_image_url", indexes = {@Index(name = "idx_recommend_image_url", columnList = "id", unique = true)})
public class RecommendImageUrl extends BaseEntity {
    @Column(name = "url", nullable = false, unique = true, columnDefinition = "TEXT")
    private String url;

    @Builder
    public RecommendImageUrl(@NonNull String url) {
        this.url = url;
    }
}
