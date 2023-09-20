package com.muscatlab.bob.domain.orderStatsHistory.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "order_status_history")
@Table(schema = "bob", name = "order_status_history", indexes = {@Index(name = "idx_order_status_history", columnList = "id", unique = true)})
public class OrderStatusHistory extends BaseEntity {

    @Column(name = "status", nullable = false, columnDefinition = "TEXT")
    private String status;

    @Builder
    public OrderStatusHistory(@NonNull String status) {
        this.status = status;
    }
}
