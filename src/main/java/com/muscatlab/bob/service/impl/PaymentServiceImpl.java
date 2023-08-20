package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.common.constant.ReturnAmountType;
import com.muscatlab.bob.common.constant.RobotStatus;
import com.muscatlab.bob.domain.entity.*;
import com.muscatlab.bob.dto.card.PaidInput;
import com.muscatlab.bob.dto.order.OrderOutput;
import com.muscatlab.bob.repository.*;
import com.muscatlab.bob.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final CustomMenuRepository customMenuRepository;
    private final MemberRepository memberRepository;
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final RobotRepository robotRepository;


    @Override
    @Transactional
    public OrderOutput paid(UUID memberId, PaidInput input) {
        Menu menu = this.menuRepository.findByName(input.getMenus().getName())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "존재하지 않는 메뉴입니다."));
        CustomMenu customMenu = this.customMenuRepository.save(input.getMenus().toEntity(menu));
        Member member = this.memberRepository.findById(memberId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "존재하지 않는 회원입니다."));
        OrderHistory orderHistory = this.orderHistoryRepository.save(OrderHistory.builder()
                .customMenu(customMenu)
                .member(member)
                .build());

        OrderStatusHistory orderStatusHistory = this.orderStatusHistoryRepository.save(OrderStatusHistory.builder()
                .status("떡을 불리고 있습니다.")
                .build());

        int orderNumber = (int) this.orderRepository.findAll().stream().count();

        Order order = this.orderRepository.save(Order.builder()
                .menu(orderHistory.getCustomMenu())
                .member(member)
                .status(OrderStatus.READY)
                .orderStatusHistories(List.of(orderStatusHistory))
                .returnAmountType(input.getReturnAmountType())
                .orderNumber(orderNumber)
                .build());

        int returnAmount = this.getReturnAmount(customMenu);

        if (input.getReturnAmountType().equals(ReturnAmountType.DONATION)) {
            this.memberRepository.save(member.addDonation(returnAmount));
        }

        if (input.getReturnAmountType().equals(ReturnAmountType.POINT)) {
            this.memberRepository.save(member.addPoint(returnAmount));
        }
        updateInitialRobotStatus();
        return OrderOutput.from(
                order,
                order.getOrderNumber(),
                this.getExpectedTime(orderHistory.getCustomMenu().getMenu()),
                input.getReturnAmountType().equals(ReturnAmountType.DONATION),
                returnAmount,
                input.getReturnAmountType().equals(ReturnAmountType.DONATION) ? member.getDonation().getAmount() : member.getPointAmount().getAmount()
        );
    }

    private int getReturnAmount(CustomMenu customMenu) {
        return customMenu.getMenu().getPrice() / 100 * customMenu.getQuantity();
    }

    private String getExpectedTime(Menu menu) {
        List<Integer> expectedTimes = this.robotRepository.findAllByMenu(menu).stream()
                .map(Robot::getExpectedTime)
                .toList();
        int expectedTime = expectedTimes.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return String.format("%02d", expectedTime + menu.getDefaultExpectedTime());
    }

    private void updateInitialRobotStatus() {
        Robot robot = this.robotRepository.findByNameLike("%떡%");
        this.robotRepository.save(robot.updateStatus(RobotStatus.PROCEEDING));
    }
}
