package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "order")
@Table(schema = "bob", name = "order", indexes = {@Index(name = "idx_order", columnList = "id", unique = true)})
public class Order extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "custo_menu_id")
    private CustomMenu menu;

    @Column(name = "status", nullable = false, columnDefinition = "TEXT")
    private String status; // 번과 패티를 굽는 중 입니다.

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name ="card_id")
    private Card card;

    @Builder
    public Order(@NonNull CustomMenu menu, @NonNull Card card, @NonNull String status) {
        this.menu = menu;
        this.card = card;
        this.status = status;
    }

    public Order updateStatus(String status) {
        this.status = status;
        return this;
    }
}
