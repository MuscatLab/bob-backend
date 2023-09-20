package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, UUID>, OrderHistoryRepositoryCustom {
}
