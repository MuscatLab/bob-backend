package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.common.constant.ReturnAmountType;
import com.muscatlab.bob.domain.customMenu.command.CustomMenuCommandService;
import com.muscatlab.bob.domain.customMenu.entity.CustomMenu;
import com.muscatlab.bob.domain.member.command.MemberCommandService;
import com.muscatlab.bob.domain.member.query.MemberQueryService;
import com.muscatlab.bob.domain.menu.query.MenuQueryService;
import com.muscatlab.bob.domain.order.command.OrderCommandService;
import com.muscatlab.bob.domain.order.command.dto.CreateOrderParam;
import com.muscatlab.bob.domain.order.query.OrderQueryService;
import com.muscatlab.bob.domain.orderHistory.command.OrderHistoryCommandService;
import com.muscatlab.bob.domain.orderStatusHistory.command.OrderStatusHistoryCommandService;
import com.muscatlab.bob.domain.robot.commend.RobotCommandService;
import com.muscatlab.bob.domain.robot.query.RobotQueryService;
import com.muscatlab.bob.dto.card.PaidInput;
import com.muscatlab.bob.dto.order.OrderOutput;
import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.order.entity.Order;
import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;
import com.muscatlab.bob.domain.orderStatusHistory.entity.OrderStatusHistory;
import com.muscatlab.bob.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderHistoryCommandService orderHistoryCommandService;
    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;
    private final MenuQueryService menuQueryService;
    private final CustomMenuCommandService customMenuCommandService;
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final OrderStatusHistoryCommandService orderStatusHistoryCommandService;
    private final RobotQueryService robotQueryService;
    private final RobotCommandService robotCommandService;


    @Override
    public OrderOutput paid(UUID memberId, PaidInput input) {
        Menu menu = this.menuQueryService.getByName(input.getMenus().getName());
        if (Objects.isNull(menu)) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "존재하지 않는 메뉴입니다.");
        }
        CustomMenu customMenu = this.customMenuCommandService.create(input.getMenus().toEntity(menu));
        Member member = this.memberQueryService.getById(memberId);
        if (Objects.isNull(member)) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "존재하지 않는 회원입니다.");
        }
        OrderHistory orderHistory = this.orderHistoryCommandService.create(customMenu, member);

        OrderStatusHistory orderStatusHistory = this.orderStatusHistoryCommandService.createWithStatusMessage("떡을 불리고 있습니다.");

        int orderNumber = this.orderQueryService.count();

        Order order = this.orderCommandService.create(CreateOrderParam.builder()
                .customMenu(orderHistory.getCustomMenu())
                .member(member)
                .status(OrderStatus.READY)
                .orderStatusHistories(List.of(orderStatusHistory))
                .returnAmountType(input.getReturnAmountType())
                .orderNumber(orderNumber)
                .build());

        int returnAmount = this.robotQueryService.getReturnAmount(customMenu);

        if (input.getReturnAmountType().equals(ReturnAmountType.DONATION)) {
            this.memberCommandService.addDonation(member, returnAmount);
        }

        if (input.getReturnAmountType().equals(ReturnAmountType.POINT)) {
            this.memberCommandService.addPoint(member, returnAmount);
        }
        this.robotCommandService.updateInitialRobotStatus();
        return OrderOutput.from(
                order,
                order.getOrderNumber(),
                this.robotQueryService.getExpectedTime(orderHistory.getCustomMenu().getMenu()),
                input.getReturnAmountType().equals(ReturnAmountType.DONATION),
                returnAmount,
                input.getReturnAmountType().equals(ReturnAmountType.DONATION) ? member.getDonation().getAmount() : member.getPointAmount().getAmount()
        );
    }
}
