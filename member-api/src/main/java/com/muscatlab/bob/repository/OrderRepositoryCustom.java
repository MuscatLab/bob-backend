package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.order.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepositoryCustom {
    List<Order> findByMemberId(UUID memberId);
}
