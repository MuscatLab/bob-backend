package com.muscatlab.bob.dto.recommendImageUrl;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.recommendImageUrl.entity.RecommendImageUrl;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecommendImageUrlOutput {
    private UUID id;

    private String url;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public static RecommendImageUrlOutput from(RecommendImageUrl entity) {
        return new RecommendImageUrlOutput()
                .setId(entity.getId())
                .setUrl(entity.getUrl())
                .setCreatedDate(entity.getCreatedDate())
                .setModifiedDate(entity.getModifiedDate());
    }
}
