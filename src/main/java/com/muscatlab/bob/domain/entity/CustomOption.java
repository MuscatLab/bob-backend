package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@Entity(name = "custom_option")
@Table(schema = "bob", name = "custom_option", indexes = {@Index(name = "idx_custom_option", columnList = "id", unique = true)})
public class CustomOption extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "step", nullable = false, columnDefinition = "integer default 0")
    private int step;

    @Builder
    public CustomOption(@NonNull String name, int step) {
        this.name = name;
        this.step = step;
    }
}
