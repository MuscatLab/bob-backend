package com.muscatlab.bob.dto.card;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.common.constant.ReturnAmountType;
import com.muscatlab.bob.dto.customMenu.CreateCustomMenuInput;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaidInput {
    private CreateCustomMenuInput menus;

    private ReturnAmountType returnAmountType;
}
