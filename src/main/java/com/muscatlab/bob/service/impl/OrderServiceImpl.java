package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.domain.entity.Menu;
import com.muscatlab.bob.domain.entity.Order;
import com.muscatlab.bob.domain.entity.Robot;
import com.muscatlab.bob.dto.order.OrderOutput;
import com.muscatlab.bob.repository.OrderRepository;
import com.muscatlab.bob.repository.RobotRepository;
import com.muscatlab.bob.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RobotRepository robotRepository;

    @Override
    public OrderOutput getByMemberId(UUID memberId) {
        int ticketNumber = (int) this.orderRepository.findAll().stream().count();
        List<Order> orders = this.orderRepository.findByMemberId(memberId);
        if (Objects.isNull(orders)) {
            return null;
        }
        Order filterdOrder = orders.stream()
                .filter(order -> order.getStatus() != OrderStatus.FINISHED)
                .findFirst()
                .orElse(null);
        if (Objects.isNull(filterdOrder)) {
            return null;
        }
        return OrderOutput.from(
                filterdOrder,
                ticketNumber,
                "00:" + this.getExpectedTime(filterdOrder.getMenu().getMenu())
        );
    }

    private String getExpectedTime(Menu menu) {
        List<Integer> expectedTimes = this.robotRepository.findAllByMenu(menu).stream()
                .map(Robot::getExpectedTime)
                .toList();
        int expectedTime = expectedTimes.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return String.format("%02d", expectedTime);
    }

    private String getReasons(Menu menu) {
        List<String> reasons = this.robotRepository.findAllByMenu(menu).stream()
                .map(Robot::getReason)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (reasons.isEmpty()) {
            return null;
        }

        return String.join(", ", reasons);
    }
}
