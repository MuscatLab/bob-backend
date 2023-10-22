package com.muscatlab.bob.domain.order.query;

import com.muscatlab.bob.domain.order.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderQueryService {
    List<Order> getAllByMemberId(UUID memberId);

    int count();
}
