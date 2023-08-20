package com.muscatlab.bob.dto.card;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.Card;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardOutput {
    private UUID id;

    private String uid;

    public static CardOutput from(Card card) {
        return new CardOutput()
                .setId(card.getId())
                .setUid(card.getUid());
    }
}