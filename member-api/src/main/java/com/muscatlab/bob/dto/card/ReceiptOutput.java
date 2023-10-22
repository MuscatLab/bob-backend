package com.muscatlab.bob.dto.card;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.customMenu.entity.CustomMenu;
import com.muscatlab.bob.dto.customMenu.CustomMenuOutput;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReceiptOutput {
    private UUID id;

    private CustomMenuOutput orderMenus;

    private int price;

    private int salePrice;

    private int amount;

    public static ReceiptOutput from(CustomMenu orderMenus, int price) {
        return new ReceiptOutput()
                .setId(orderMenus.getId())
                .setOrderMenus(CustomMenuOutput.from(orderMenus))
                .setPrice(price);
    }
}
