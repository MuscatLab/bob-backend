package com.muscatlab.bob.domain.order.command.impl;

import com.muscatlab.bob.domain.order.command.OrderCommandService;
import com.muscatlab.bob.domain.order.command.dto.CreateOrderParam;
import com.muscatlab.bob.domain.order.entity.Order;
import com.muscatlab.bob.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderRepository orderRepository;

    @Override
    public Order create(CreateOrderParam param) {
        return orderRepository.save(param.toEntity());
    }
}
