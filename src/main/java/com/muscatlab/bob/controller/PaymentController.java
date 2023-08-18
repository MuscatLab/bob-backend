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

@Tag(name = "결제", description = "결제 관련 API")
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @Operation(summary = "영수증 발급")
    @PostMapping("/receipt/{cardUid}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiptOutput checkout(
            @PathVariable(value = "cardUid") String cardUid,
            @RequestBody @Valid CheckoutPaymentInput input
    ) {
        return this.service.checkout(cardUid, input);
    }

    @Operation(summary = "결제 완료")
    @PostMapping("/paid/{cardUid}")
    @ResponseStatus(HttpStatus.OK)
    public boolean paid(
            @PathVariable(value = "cardUid") String cardUid,
            @RequestBody @Valid PaidInput input
    ) {
        return this.service.paid(cardUid, input);
    }
}
