package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "option")
@Table(schema = "bob", name = "option", indexes = {@Index(name = "idx_option", columnList = "id", unique = true)})
public class Option extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "quantity", joinColumns = @JoinColumn(name = "id"))
    private Set<Integer> quantity = new HashSet<>();

    @Builder
    public Option(@NonNull String name, @NonNull List<Integer> quantity) {
        this.name = name;
        this.quantity = new HashSet<>(quantity);
    }
}
