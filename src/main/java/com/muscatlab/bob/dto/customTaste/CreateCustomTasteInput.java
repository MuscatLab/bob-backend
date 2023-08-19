package com.muscatlab.bob.dto.customTaste;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.CustomTaste;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateCustomTasteInput {
    private String name;

    private int step;

    public CustomTaste toEntity() {
        return CustomTaste.builder()
                .name(this.name)
                .step(this.step)
                .build();
    }
}
