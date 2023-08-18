package com.muscatlab.bob.dto.order;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderOutput {
    private UUID id;

    private int ticketNumber;

    private String status;

    private String expectedTime; // 10분

    private String reason; // 오래 걸리는 이유

    public static OrderOutput from(Order order, int ticketNumber, String expectedTime, String reason) {
        return new OrderOutput()
                .setId(order.getId())
                .setTicketNumber(ticketNumber)
                .setStatus(order.getStatus())
                .setExpectedTime(expectedTime)
                .setReason(reason);
    }
}
