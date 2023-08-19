package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.common.constant.ReturnAmountType;
import com.muscatlab.bob.domain.entity.*;
import com.muscatlab.bob.dto.card.PaidInput;
import com.muscatlab.bob.repository.*;
import com.muscatlab.bob.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    @Transactional
    public boolean paid(UUID memberId, PaidInput input) {
        Menu menu = this.menuRepository.findByName(input.getMenus().getName())
                .orElseThrow();
        CustomMenu customMenu = this.customMenuRepository.save(input.getMenus().toEntity(menu));
        Member member = this.memberRepository.findById(memberId)
                .orElseThrow();
        OrderHistory orderHistory = this.orderHistoryRepository.save(OrderHistory.builder()
                .customMenu(customMenu)
                .member(member)
                .build());

        OrderStatusHistory orderStatusHistory = this.orderStatusHistoryRepository.save(OrderStatusHistory.builder()
                .status("떡을 불리고 있습니다.")
                .build());

        this.orderRepository.save(Order.builder()
                .menu(orderHistory.getCustomMenu())
                .member(member)
                .status(OrderStatus.READY)
                .orderStatusHistories(List.of(orderStatusHistory))
                .build());

        if (input.getReturnAmountType().equals(ReturnAmountType.DONATION)) {
            this.memberRepository.save(member.addDonation(this.getReturnAmount(customMenu)));
        }

        if (input.getReturnAmountType().equals(ReturnAmountType.POINT)) {
            this.memberRepository.save(member.addPoint(this.getReturnAmount(customMenu)));
        }

        return true;
    }

    private int getReturnAmount(CustomMenu customMenu) {
        int amount = customMenu.getMenu().getPrice() - this.getDiscountPrice(customMenu.getMenu().getPrice(), customMenu.getQuantity());
        if (amount == 0) return amount;

        int optionCount = customMenu.getMenu().getOptions().size();
        int customOptionCount = customMenu.getCustomOptions().size();
        if (optionCount == 0 || customOptionCount == 0) return amount;
        int optionPercent = 100 / optionCount;

        for (CustomOption customOption : customMenu.getCustomOptions()) {
            int optionPrice = this.getDiscountPrice(customMenu.getMenu().getPrice(), optionPercent);
            amount -= this.getDiscountPrice(optionPrice, customOption.getQuantity());
        }
        return amount;
    }

    private int getDiscountPrice(int price, int quantity) {
        return price / 100 * quantity;
    }
}
