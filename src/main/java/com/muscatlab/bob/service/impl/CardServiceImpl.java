package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.dto.card.CardOutput;
import com.muscatlab.bob.repository.CardRepository;
import com.muscatlab.bob.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository repository;

    @Override
    public CardOutput getDefaultCard() {
        return this.repository.findAll().stream()
                .map(CardOutput::from)
                .findFirst()
                .orElseThrow();
    }
}
