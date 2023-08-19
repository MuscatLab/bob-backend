package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.card.PaidInput;

import java.util.UUID;

public interface PaymentService {
    boolean paid(UUID memberId, PaidInput input);
}
