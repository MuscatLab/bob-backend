package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.card.PaidInput;
import com.muscatlab.bob.dto.order.OrderOutput;

import java.util.UUID;

public interface PaymentService {
    OrderOutput paid(UUID memberId, PaidInput input);
}
