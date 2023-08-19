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
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default false")
    private boolean status;

    @Builder
    public OrderHistory(@NonNull CustomMenu customMenu, @NonNull Member member, boolean status) {
        this.customMenu = customMenu;
        this.member = member;
        this.status = status;
    }

    public OrderHistory updateStatus(boolean status) {
        this.status = status;
        return this;
    }
}
