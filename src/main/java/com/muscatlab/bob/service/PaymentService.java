package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.card.CheckoutPaymentInput;
import com.muscatlab.bob.dto.card.PaidInput;
import com.muscatlab.bob.dto.card.ReceiptOutput;

import java.util.UUID;

public interface PaymentService {
    ReceiptOutput checkout(UUID memberId, CheckoutPaymentInput input);

    boolean paid(UUID memberId, PaidInput input);
}
