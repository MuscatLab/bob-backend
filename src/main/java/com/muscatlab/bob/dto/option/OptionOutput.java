package com.muscatlab.bob.dto.option;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.Option;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OptionOutput {
    private UUID id;

    private String name;

    private List<Integer> quantity;

    public static OptionOutput from(Option entity) {
        return new OptionOutput()
                .setId(entity.getId())
                .setName(entity.getName())
                .setQuantity(new ArrayList<>(entity.getQuantity()));
    }
}
