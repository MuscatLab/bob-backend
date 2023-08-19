package com.muscatlab.bob.dto.order;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.domain.entity.Order;
import com.muscatlab.bob.domain.entity.OrderStatusHistory;
import com.muscatlab.bob.dto.orderStatusHIstory.OrderStatusHistoryOutput;
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
public class OrderOutput {
    private UUID id;

    private int ticketNumber;

    private OrderStatus status;

    private String expectedTime; // 10분

    private List<OrderStatusHistoryOutput> orderStatusHistories;

    private int donationAmount;

    private int totalDonationAmount;

    private int pointAmount;

    private int totalPointAmount;

    public static OrderOutput from(Order order, int ticketNumber, String expectedTime) {
        return new OrderOutput()
                .setId(order.getId())
                .setTicketNumber(ticketNumber)
                .setStatus(order.getStatus())
                .setExpectedTime(expectedTime)
                .setOrderStatusHistories(order.getOrderStatusHistories().stream()
                        .map(OrderStatusHistoryOutput::from)
                        .collect(Collectors.toList()))
                .setDonationAmount(0)
                .setTotalDonationAmount(0)
                .setPointAmount(0)
                .setTotalPointAmount(0);
    }

    public static OrderOutput from(Order order, int ticketNumber, String expectedTime, boolean isDonation, int amount, int totalAmount) {
        return new OrderOutput()
                .setId(order.getId())
                .setTicketNumber(ticketNumber)
                .setStatus(order.getStatus())
                .setExpectedTime(expectedTime)
                .setOrderStatusHistories(order.getOrderStatusHistories().stream()
                        .map(OrderStatusHistoryOutput::from)
                        .collect(Collectors.toList()))
                .setDonationAmount(isDonation ? amount : 0)
                .setTotalDonationAmount(isDonation ? totalAmount : 0)
                .setPointAmount(isDonation ? 0 : amount)
                .setTotalPointAmount(isDonation ? 0 : totalAmount);
    }
}
