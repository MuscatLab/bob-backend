package com.muscatlab.bob.domain.order.query.impl;

import com.muscatlab.bob.domain.order.entity.Order;
import com.muscatlab.bob.domain.order.query.OrderQueryService;
import com.muscatlab.bob.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllByMemberId(UUID memberId) {
        return this.orderRepository.findByMemberId(memberId);
    }

    @Override
    public int count() {
        return (int) this.orderRepository.count();
    }
}
