package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.order.OrderOutput;

import java.util.UUID;

public interface OrderService {
    OrderOutput getByMemberId(UUID memberId);
}
