package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "order_history")
@Table(schema = "bob", name = "order_history", indexes = {@Index(name = "idx_order_history", columnList = "id", unique = true)})
public class OrderHistory extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "custom_menu_id")
    private CustomMenu customMenu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public OrderHistory(@NonNull CustomMenu customMenu, @NonNull Member member) {
        this.customMenu = customMenu;
        this.member = member;
    }
}
