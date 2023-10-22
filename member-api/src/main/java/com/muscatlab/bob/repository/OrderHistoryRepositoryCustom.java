package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderHistoryRepositoryCustom {
    List<OrderHistory> findAllByMemberId(UUID memberId);

    Optional<OrderHistory> findByCustomMenuId(UUID id);
}
