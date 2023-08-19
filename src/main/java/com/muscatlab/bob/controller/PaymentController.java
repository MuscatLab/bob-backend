package com.muscatlab.bob.controller;

import com.muscatlab.bob.dto.card.CheckoutPaymentInput;
import com.muscatlab.bob.dto.card.PaidInput;
import com.muscatlab.bob.dto.card.ReceiptOutput;
import com.muscatlab.bob.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "결제", description = "결제 관련 API")
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @Operation(summary = "영수증 발급")
    @PostMapping("/receipt/{memberId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiptOutput checkout(
            @PathVariable(value = "memberId") UUID memberId,
            @RequestBody @Valid CheckoutPaymentInput input
    ) {
        return this.service.checkout(memberId, input);
    }

    @Operation(summary = "결제 완료")
    @PostMapping("/paid/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean paid(
            @PathVariable(value = "memberId") UUID memberId,
            @RequestBody @Valid PaidInput input
    ) {
        return this.service.paid(memberId, input);
    }
}
