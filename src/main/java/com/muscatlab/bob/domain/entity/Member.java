package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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

    @Builder
    public Member(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public boolean validate(String password) {
        return this.password.equals(password);
    }
}
