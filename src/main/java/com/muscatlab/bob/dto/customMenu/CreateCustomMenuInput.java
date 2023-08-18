package com.muscatlab.bob.dto.customMenu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.CustomMenu;
import com.muscatlab.bob.domain.entity.Menu;
import com.muscatlab.bob.dto.customOption.CreateCustomOptionInput;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateCustomMenuInput {
    private String name;

    private List<CreateCustomOptionInput> options;

    public CustomMenu toEntity(Menu menu) {
        return CustomMenu.builder()
                .menu(menu)
                .customOptions(this.options.stream()
                        .map(CreateCustomOptionInput::toEntity)
                        .toList())
                .build();
    }
}
