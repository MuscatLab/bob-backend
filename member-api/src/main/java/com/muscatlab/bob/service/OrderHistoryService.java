package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.customMenu.CustomMenuOutput;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {
    List<CustomMenuOutput> getAllByMemberId(UUID memberId);
}
