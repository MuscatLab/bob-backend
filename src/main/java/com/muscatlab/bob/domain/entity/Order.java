package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.constant.OrderStatus;
import com.muscatlab.bob.common.constant.ReturnAmountType;
import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "order")
@Table(schema = "bob", name = "order", indexes = {@Index(name = "idx_order", columnList = "id", unique = true)})
public class Order extends BaseEntity {
    @Column(name = "order_number")
    private int orderNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "custo_menu_id")
    private CustomMenu menu;

    @Column(name = "status", nullable = false, columnDefinition = "TEXT")
    private OrderStatus status; // 현재 어떤 것을 만들고 있습니다.

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name ="member_id")
    private Member member;

    @Column(name = "return_amount_tyep")
    private ReturnAmountType returnAmountType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_status_history_id")
    private List<OrderStatusHistory> orderStatusHistories = new ArrayList<>();

    @Builder
    public Order(@NonNull CustomMenu menu,
                 @NonNull Member member,
                 @NonNull OrderStatus status,
                 List<OrderStatusHistory> orderStatusHistories,
                 ReturnAmountType returnAmountType, int orderNumber) {
        this.menu = menu;
        this.member = member;
        this.status = status;
        this.orderStatusHistories = orderStatusHistories;
        this.returnAmountType = returnAmountType;
        this.orderNumber = orderNumber;
    }

    public Order updateStatus(OrderStatus status) {
        this.status = status;
        return this;
    }
}
