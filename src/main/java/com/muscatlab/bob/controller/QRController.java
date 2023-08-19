package com.muscatlab.bob.controller;

import com.muscatlab.bob.dto.order.OrderOutput;
import com.muscatlab.bob.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "QR", description = "QR 관련 API")
@RestController
@RequestMapping("/qr")
@RequiredArgsConstructor
public class QRController {
    private final OrderService service;

    @Operation(summary = "QR 코드로 주문 내역 조회")
    @GetMapping("{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderOutput getByCardUid(
            @PathVariable(value = "memberId") UUID memberId
            ) {
        return this.service.getByMemberId(memberId);
    }
}
