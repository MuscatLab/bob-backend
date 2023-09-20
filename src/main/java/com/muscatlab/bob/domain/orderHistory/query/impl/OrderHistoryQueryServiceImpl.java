package com.muscatlab.bob.domain.orderHistory.query.impl;

import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;
import com.muscatlab.bob.domain.orderHistory.query.OrderHistoryQueryService;
import com.muscatlab.bob.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderHistoryQueryServiceImpl implements OrderHistoryQueryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public List<OrderHistory> getAllByMemberId(UUID memberId) {
        return this.orderHistoryRepository.findAllByMemberId(memberId);
    }
}
