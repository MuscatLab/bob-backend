package com.muscatlab.bob.dto.orderStatusHIstory;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.orderStatsHistory.entity.OrderStatusHistory;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderStatusHistoryOutput {
    private UUID id;

    private String status;

    public static OrderStatusHistoryOutput from(OrderStatusHistory entity) {
        return new OrderStatusHistoryOutput()
                .setId(entity.getId())
                .setStatus(entity.getStatus());
    }
}
