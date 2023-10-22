package com.muscatlab.bob.domain.order.command.dto;

import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.common.constant.ReturnAmountType;
import com.muscatlab.bob.domain.customMenu.entity.CustomMenu;
import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.domain.order.entity.Order;
import com.muscatlab.bob.domain.orderStatusHistory.entity.OrderStatusHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;


@Getter
@NoArgsConstructor
public class CreateOrderParam {
    private CustomMenu customMenu;

    private Member member;

    private OrderStatus status;

    private List<OrderStatusHistory> orderStatusHistories;

    private ReturnAmountType returnAmountType;

    private int orderNumber;

    @Builder
    public CreateOrderParam(
            @NonNull CustomMenu customMenu,
            @NonNull Member member,
            @NonNull OrderStatus status,
            @NonNull List<OrderStatusHistory> orderStatusHistories,
            @NonNull ReturnAmountType returnAmountType,
            @NonNull int orderNumber
    ) {
        this.customMenu = customMenu;
        this.member = member;
        this.status = status;
        this.orderStatusHistories = orderStatusHistories;
        this.returnAmountType = returnAmountType;
        this.orderNumber = orderNumber;
    }

    public Order toEntity() {
        return Order.builder()
                .menu(this.customMenu)
                .member(this.member)
                .status(this.status)
                .orderStatusHistories(this.orderStatusHistories)
                .returnAmountType(this.returnAmountType)
                .orderNumber(this.orderNumber)
                .build();
    }
}
