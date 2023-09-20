package com.muscatlab.bob.customTaste;

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
@Entity(name = "custom_taste")
@Table(schema = "bob", name = "custom_taste", indexes = {@Index(name = "idx_custom_taste", columnList = "id", unique = true)})
public class CustomTaste extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "step", nullable = false, columnDefinition = "integer default 0")
    private Integer step;

    @Builder
    public CustomTaste(@NonNull String name, @NonNull Integer step) {
        this.name = name;
        this.step = step;
    }
}
