package com.muscatlab.bob.dto.customMenu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.CustomMenu;
import com.muscatlab.bob.dto.customTaste.CustomTasteOutput;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
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

    private int quantity;

    private String imageUrl;

    private List<CustomTasteOutput> tastes;

    private LocalDateTime createdDate;

    public static CustomMenuOutput from(CustomMenu entity) {
        return new CustomMenuOutput()
                .setId(entity.getId())
                .setName(entity.getMenu().getName())
                .setQuantity(entity.getQuantity())
                .setImageUrl(entity.getMenu().getImageUrl())
                .setTastes(entity.getCustomTastes().stream()
                        .map(CustomTasteOutput::from)
                        .collect(Collectors.toList()))
                .setCreatedDate(entity.getCreatedDate());
    }
}
