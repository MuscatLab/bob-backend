package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "option")
@Table(schema = "bob", name = "option", indexes = {@Index(name = "idx_option", columnList = "id", unique = true)})
public class Option extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "step", nullable = false, columnDefinition = "integer default 0")
    private int step;

    @Builder
    public Option(@NonNull String name, int step) {
        this.name = name;
        this.step = step;
    }
}
