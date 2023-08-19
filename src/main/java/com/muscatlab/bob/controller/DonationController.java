package com.muscatlab.bob.controller;

import com.muscatlab.bob.service.DonationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "기부", description = "기부 관련 API")
@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService service;

    @Operation(summary = "기부 총액 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public int getAllDonationAmount() {
        return this.service.getAllDonationAmount();
    }
}
