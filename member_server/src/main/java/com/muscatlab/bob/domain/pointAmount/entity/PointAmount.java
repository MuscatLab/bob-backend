package com.muscatlab.bob.domain.pointAmount.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "point_amount")
@Table(schema = "bob", name = "point_amount", indexes = {@Index(name = "idx_point_amount", columnList = "id", unique = true)})
public class PointAmount extends BaseEntity {
    private int amount;

    @Builder
    public PointAmount(int amount) {
        this.amount = amount;
    }

    public void add(int amount) {
        this.amount += amount;
    }
}
