package com.muscatlab.bob.dto.menu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.Menu;
import com.muscatlab.bob.dto.option.CreateOptionInput;
import com.muscatlab.bob.dto.taste.CreateTasteInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateMenuInput {
    @NotEmpty
    @NotBlank(message = "메뉴 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "메뉴 가격을 입력해주세요.")
    private int price;

    @NotNull(message = "메뉴 이미지를 입력해주세요.")
    private String imageUrl;

    @NotNull(message = "메뉴 옵션을 입력해주세요.")
    private List<CreateOptionInput> options;

    @NotNull(message = "메뉴 맛을 입력해주세요.")
    private List<CreateTasteInput> tastes;

    @NotNull
    private List<UUID> robotIds;

    public Menu toEntity() {
        return Menu.builder()
                .name(this.name)
                .price(this.price)
                .imageUrl(this.imageUrl)
                .options(this.options.stream()
                        .map(CreateOptionInput::toEntity)
                        .collect(Collectors.toList()))
                .tastes(this.tastes.stream()
                        .map(CreateTasteInput::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
