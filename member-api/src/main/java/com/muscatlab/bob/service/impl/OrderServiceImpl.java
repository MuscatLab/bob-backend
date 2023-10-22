package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.common.constant.ReturnAmountType;
import com.muscatlab.bob.domain.order.query.OrderQueryService;
import com.muscatlab.bob.domain.robot.query.RobotQueryService;
import com.muscatlab.bob.dto.order.OrderOutput;
import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.domain.order.entity.Order;
import com.muscatlab.bob.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderQueryService orderQueryService;
    private final RobotQueryService robotQueryService;

    @Override
    public OrderOutput getByMemberId(UUID memberId) {
        List<Order> orders = this.orderQueryService.getAllByMemberId(memberId);
        if (orders.isEmpty()) {
            return null;
        }
        Order filterdOrder = orders.stream()
                .filter(order -> order.getStatus() != OrderStatus.FINISHED)
                .findFirst()
                .orElse(null);
        if (Objects.isNull(filterdOrder)) {
            return null;
        }

        int returnAmount = this.robotQueryService.getReturnAmount(filterdOrder.getMenu());

        Member member = filterdOrder.getMember();

        return OrderOutput.from(
                filterdOrder,
                filterdOrder.getOrderNumber(),
                this.robotQueryService.getExpectedTime(filterdOrder.getMenu().getMenu()),
                filterdOrder.getReturnAmountType().equals(ReturnAmountType.DONATION),
                returnAmount,
                filterdOrder.getReturnAmountType().equals(ReturnAmountType.DONATION) ? member.getDonation().getAmount() : member.getPointAmount().getAmount()
        );
    }
}
