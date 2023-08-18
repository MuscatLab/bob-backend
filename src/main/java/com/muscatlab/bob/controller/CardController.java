package com.muscatlab.bob.controller;

import com.muscatlab.bob.dto.card.CardOutput;
import com.muscatlab.bob.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카드", description = "카드 관련 API")
@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService service;

    @Operation(summary = "카드 기본 정보 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CardOutput getDefaultCard() {
        return this.service.getDefaultCard();
    }
}
