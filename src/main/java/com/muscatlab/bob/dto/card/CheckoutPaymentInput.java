package com.muscatlab.bob.dto.card;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.common.constant.ReturnAmountType;
import com.muscatlab.bob.dto.customMenu.CreateCustomMenuInput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CheckoutPaymentInput {
    private CreateCustomMenuInput menus;

    private ReturnAmountType returnAmountType;
}
