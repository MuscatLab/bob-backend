package com.muscatlab.bob.domain.orderHistory.command.impl;

import com.muscatlab.bob.domain.customMenu.entity.CustomMenu;
import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.domain.orderHistory.command.OrderHistoryCommandService;
import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;
import com.muscatlab.bob.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHistoryCommandServiceImpl implements OrderHistoryCommandService {
    private final OrderHistoryRepository orderHistoryRepository;


    @Override
    public OrderHistory create(CustomMenu customMenu, Member member) {
        return this.orderHistoryRepository.save(OrderHistory.builder()
                .customMenu(customMenu)
                .member(member)
                .build());
    }
}
