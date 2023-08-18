package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.order.OrderOutput;

public interface OrderService {
    OrderOutput getByCardUid(String cardUid);
}
