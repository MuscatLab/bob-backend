package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.customMenu.CustomMenuOutput;

import java.util.List;

public interface OrderHistoryService {
    List<CustomMenuOutput> getAllByCardUid(String cardUid);
}
