package com.muscatlab.bob.service.impl;

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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RobotRepository robotRepository;

    @Override
    public OrderOutput getByCardUid(String cardUid) {
        int ticketNumber = (int) this.orderRepository.findAll().stream().count();
        Order order = this.orderRepository.findByCardUid(cardUid)
                .orElseThrow();
        if (order.getStatus().equals("FINISHED")) {
            return null;
        }
        return OrderOutput.from(
                order,
                ticketNumber,
                "00:" + this.getExpectedTime(order.getMenu().getMenu()),
                this.getReasons(order.getMenu().getMenu())
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
