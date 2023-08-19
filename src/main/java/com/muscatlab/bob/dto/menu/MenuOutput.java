package com.muscatlab.bob.dto.menu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.Menu;
import com.muscatlab.bob.dto.option.OptionOutput;
import com.muscatlab.bob.dto.taste.TasteOutput;
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
public class MenuOutput {
    private UUID id;

    private String name;

    private int price;

    private String imageUrl;

    private List<OptionOutput> options;

    private List<TasteOutput> tastes;

    private String expectedTime; // 10분

    private String reason; // 늦는 이유

    public static MenuOutput from(Menu entity, String expectedTime, String reason) {
        return new MenuOutput()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPrice(entity.getPrice())
                .setImageUrl(entity.getImageUrl())
                .setOptions(entity.getOptions().stream()
                        .map(OptionOutput::from)
                        .collect(Collectors.toList()))
                .setTastes(entity.getTastes().stream()
                        .map(TasteOutput::from)
                        .collect(Collectors.toList()))
                .setExpectedTime(expectedTime)
                .setReason(reason);
    }
}
