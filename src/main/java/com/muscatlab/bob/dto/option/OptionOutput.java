package com.muscatlab.bob.dto.option;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.Option;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OptionOutput {
    private UUID id;

    private String name;

    private int maxStep;

    private boolean highLight;

    public static OptionOutput from(Option entity) {
        return new OptionOutput()
                .setId(entity.getId())
                .setName(entity.getName())
                .setMaxStep(entity.getStep());
    }

    public static OptionOutput from(Option entity, boolean highLight) {
        return new OptionOutput()
                .setId(entity.getId())
                .setName(entity.getName())
                .setMaxStep(entity.getStep())
                .setHighLight(highLight);
    }
}
