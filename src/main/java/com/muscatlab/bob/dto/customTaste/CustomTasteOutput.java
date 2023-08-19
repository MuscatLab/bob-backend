package com.muscatlab.bob.dto.customTaste;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.CustomTaste;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomTasteOutput {
    private UUID id;

    private String name;

    private int step;

    public static CustomTasteOutput from(CustomTaste entity) {
        return new CustomTasteOutput()
                .setId(entity.getId())
                .setName(entity.getName())
                .setStep(entity.getStep());
    }
}
