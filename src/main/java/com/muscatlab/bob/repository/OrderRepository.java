package com.muscatlab.bob.repository;

import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByMemberId(UUID memberId);
}
