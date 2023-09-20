package com.muscatlab.bob.domain.orderStatusHistory.command.impl;

import com.muscatlab.bob.domain.orderStatusHistory.command.OrderStatusHistoryCommandService;
import com.muscatlab.bob.domain.orderStatusHistory.entity.OrderStatusHistory;
import com.muscatlab.bob.repository.OrderStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderStatusHistoryCommandServiceImpl implements OrderStatusHistoryCommandService {
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;

    @Override
    public OrderStatusHistory createWithStatusMessage(String statusMessage) {
        return this.orderStatusHistoryRepository.save(OrderStatusHistory.builder()
                .status(statusMessage)
                .build());
    }
}
