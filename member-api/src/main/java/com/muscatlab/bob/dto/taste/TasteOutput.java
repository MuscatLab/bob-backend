package com.muscatlab.bob.dto.taste;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.taste.entity.Taste;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TasteOutput {
    private UUID id;

    private String name;

    private List<String> steps;

    public static TasteOutput from(Taste entity) {
        return new TasteOutput()
                .setId(entity.getId())
                .setName(entity.getName())
                .setSteps(entity.getSteps().stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList()));
    }
}
