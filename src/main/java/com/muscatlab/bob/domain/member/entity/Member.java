package com.muscatlab.bob.domain.member.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import com.muscatlab.bob.domain.order.entity.Order;
import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;
import com.muscatlab.bob.domain.pointAmount.entity.PointAmount;
import com.muscatlab.bob.domain.donation.entity.Donation;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
@Table(schema = "bob", name = "member", indexes = {@Index(name = "idx_member", columnList = "id", unique = true)})
public class Member extends BaseEntity {
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_history_id")
    private Set<OrderHistory> orderHistories = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Set<Order> orders = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "donation_id")
    private Donation donation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "point_id")
    private PointAmount pointAmount;

    @Builder
    public Member(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public boolean validate(String password) {
        return this.password.equals(password);
    }

    public Member addPoint(int amount) {
        if (Objects.isNull(this.pointAmount)) {
            this.pointAmount = PointAmount.builder()
                    .amount(amount)
                    .build();
        } else {
            this.pointAmount.add(amount);
        }

        return this;
    }

    public Member addDonation(int amount) {
        if (Objects.isNull(this.donation)) {
            this.donation = Donation.builder()
                    .amount(amount)
                    .build();
        } else {
            this.donation.add(amount);
        }

        return this;
    }
}
