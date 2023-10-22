package com.muscatlab.bob.dto.taste;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.taste.entity.Taste;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTasteInput {
    private String name;

    private List<Integer> steps;

    public Taste toEntity() {
        return Taste.builder()
                .name(name)
                .steps(steps)
                .build();
    }
}
