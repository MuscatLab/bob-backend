package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, UUID> {
    List<OrderHistory> findAllByMemberId(UUID memberId);

    Optional<OrderHistory> findByCustomMenuId(UUID id);
}