package com.muscatlab.bob.dto.customOption;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.CustomOption;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateCustomOptionInput {
    private String name;

    private int step;

    public CustomOption toEntity() {
        return CustomOption.builder()
                .name(this.name)
                .step(this.step)
                .build();
    }
}
