package com.muscatlab.bob.domain.orderHistory.query;

import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryQueryService {
    List<OrderHistory> getAllByMemberId(UUID memberId);
}
