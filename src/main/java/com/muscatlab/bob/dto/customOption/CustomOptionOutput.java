package com.muscatlab.bob.dto.customOption;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.CustomOption;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomOptionOutput {
    private UUID id;

    private String name;

    private int step;

    public static CustomOptionOutput from(CustomOption entity) {
        return new CustomOptionOutput()
                .setId(entity.getId())
                .setName(entity.getName())
                .setStep(entity.getStep());
    }
}
