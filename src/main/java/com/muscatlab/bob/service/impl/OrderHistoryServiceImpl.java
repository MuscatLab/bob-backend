package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.dto.customMenu.CustomMenuOutput;
import com.muscatlab.bob.repository.OrderHistoryRepository;
import com.muscatlab.bob.service.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private final OrderHistoryRepository repository;

    @Override
    public List<CustomMenuOutput> getAllByMemberId(UUID memberId) {
        List<CustomMenuOutput> menus = this.repository.findAllByMemberId(memberId).stream()
                .map(orderHistory -> CustomMenuOutput.from(orderHistory.getCustomMenu()))
                .collect(Collectors.toList());

        return menus.stream()
                .collect(Collectors.toMap(CustomMenuOutput::getName, customMenuOutput -> customMenuOutput, (customMenuOutput, customMenuOutput2) -> customMenuOutput))
                .values().stream()
                .collect(Collectors.toList());
    }
}