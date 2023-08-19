package com.muscatlab.bob.dto.customMenu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.CustomMenu;
import com.muscatlab.bob.domain.entity.Menu;
import com.muscatlab.bob.dto.customOption.CreateCustomOptionInput;
import com.muscatlab.bob.dto.customTaste.CreateCustomTasteInput;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateCustomMenuInput {
    private String name;

    private int quantity;

    private List<CreateCustomOptionInput> options;

    private List<CreateCustomTasteInput> tastes;

    public CustomMenu toEntity(Menu menu) {
        return CustomMenu.builder()
                .menu(menu)
                .quantity(this.quantity)
                .customOptions(this.options.stream()
                        .map(CreateCustomOptionInput::toEntity)
                        .toList())
                .customTastes(this.tastes.stream()
                        .map(CreateCustomTasteInput::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
