package com.muscatlab.bob.domain.donation.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "donation")
@Table(schema = "bob", name = "donation", indexes = {@Index(name = "idx_donation", columnList = "id", unique = true)})
public class Donation extends BaseEntity {
    @Column(name = "amount", nullable = false, columnDefinition = "integer default 0")
    private int amount;

    @Builder
    public Donation(int amount) {
        this.amount = amount;
    }

    public void add(int amount) {
        this.amount += amount;
    }
}
