package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "order_history")
@Table(schema = "bob", name = "order_history", indexes = {@Index(name = "idx_menu_history", columnList = "id", unique = true)})
public class OrderHistory extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "custom_menu_id")
    private CustomMenu customMenu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default false")
    private boolean status;

    @Builder
    public OrderHistory(@NonNull CustomMenu customMenu, @NonNull Card card, boolean status) {
        this.customMenu = customMenu;
        this.card = card;
        this.status = status;
    }

    public OrderHistory updateStatus(boolean status) {
        this.status = status;
        return this;
    }
}
