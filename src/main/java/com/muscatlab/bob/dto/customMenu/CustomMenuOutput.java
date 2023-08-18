package com.muscatlab.bob.dto.customMenu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.CustomMenu;
import com.muscatlab.bob.domain.entity.CustomOption;
import com.muscatlab.bob.dto.customOption.CustomOptionOutput;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomMenuOutput {
    private UUID id;

    private String name;

    private int price;

    private String imageUrl;

    private List<CustomOptionOutput> options;

    private LocalDateTime createdDate;

    public static CustomMenuOutput from(CustomMenu entity) {
        return new CustomMenuOutput()
                .setId(entity.getId())
                .setName(entity.getMenu().getName())
                .setPrice(entity.getMenu().getPrice())
                .setImageUrl(entity.getMenu().getImageUrl())
                .setOptions(entity.getCustomOptions().stream()
                        .map(CustomOptionOutput::from)
                        .collect(Collectors.toList()))
                .setCreatedDate(entity.getCreatedDate());
    }
}
