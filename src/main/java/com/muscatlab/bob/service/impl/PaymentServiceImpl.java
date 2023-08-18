package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.domain.entity.*;
import com.muscatlab.bob.dto.card.CheckoutPaymentInput;
import com.muscatlab.bob.dto.card.PaidInput;
import com.muscatlab.bob.dto.card.ReceiptOutput;
import com.muscatlab.bob.repository.*;
import com.muscatlab.bob.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final CustomMenuRepository customMenuRepository;
    private final CardRepository cardRepository;

    @Override
    @Transactional
    public ReceiptOutput checkout(String cardUid, CheckoutPaymentInput input) {
        Menu menu = this.menuRepository.findByName(input.getMenus().getName())
                .orElseThrow();
        CustomMenu customMenu = this.customMenuRepository.save(input.getMenus().toEntity(menu));
        Card card = this.cardRepository.findByUid(cardUid)
                .orElseThrow();
        this.orderHistoryRepository.save(OrderHistory.builder()
                .customMenu(customMenu)
                .card(card)
                .status(false)
                .build());
        return ReceiptOutput.from(customMenu, menu.getPrice());
    }

    @Override
    @Transactional
    public boolean paid(String cardUid, PaidInput input) {
        Card card = this.cardRepository.findByUid(cardUid)
                .orElseThrow();
        OrderHistory orderHistory = this.orderHistoryRepository.findByCustomMenuId(input.getReceiptId())
                .orElseThrow();

        this.orderRepository.save(Order.builder()
                .menu(orderHistory.getCustomMenu())
                .card(card)
                .status("번과 패티를 굽는 중 입니다.")
                .build());

        if (!input.isAcceptPrivacyPolicy()) {
            this.customMenuRepository.deleteById(orderHistory.getCustomMenu().getId());
            this.orderHistoryRepository.deleteById(orderHistory.getId());
            return true;
        }

        if (card.getId().equals(orderHistory.getCard().getId())) {
            this.orderHistoryRepository.save(orderHistory.updateStatus(true));
            return true;
        }
        return false;
    }
}
