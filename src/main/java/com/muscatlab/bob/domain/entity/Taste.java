package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "taste")
@Table(schema = "bob", name = "taste", indexes = {@Index(name = "idx_taste", columnList = "id", unique = true)})
public class Taste extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "steps", joinColumns = @JoinColumn(name = "id"))
    private Set<Integer> steps = new HashSet<>();

    @Builder
    public Taste(@NonNull String name, @NonNull List<Integer> steps) {
        this.name = name;
        this.steps = new HashSet<>(steps);
    }
}
