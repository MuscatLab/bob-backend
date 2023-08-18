package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.card.CheckoutPaymentInput;
import com.muscatlab.bob.dto.card.PaidInput;
import com.muscatlab.bob.dto.card.ReceiptOutput;

public interface PaymentService {
    ReceiptOutput checkout(String cardUid, CheckoutPaymentInput input);

    boolean paid(String cardUid, PaidInput input);
}
