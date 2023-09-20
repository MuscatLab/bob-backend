package com.muscatlab.bob.domain.card.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import com.muscatlab.bob.domain.order.entity.Order;
import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "card")
@Table(schema = "bob", name = "card", indexes = {@Index(name = "idx_card", columnList = "id", unique = true)})
public class Card extends BaseEntity {
    @Column(name = "uid", nullable = false, unique = true)
    private String uid;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "menu_history_id")
    private Set<OrderHistory> orderHistory = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Set<Order> orders = new HashSet<>();

    @Builder
    public Card(@NonNull String uid, @NonNull List<OrderHistory> menuHistories) {
        this.uid = uid;
        this.orderHistory = new HashSet<>(menuHistories);
    }
}
