package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "custom_menu")
@Table(schema = "bob", name = "custom_menu", indexes = {@Index(name = "idx_custom_menu", columnList = "id", unique = true)})
public class CustomMenu extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "quantity", nullable = false, columnDefinition = "integer default 0")
    private int quantity;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "custom_menu_custom_taste",
            joinColumns = @JoinColumn(name = "custom_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "custom_taste_id"))
    private Set<CustomTaste> customTastes = new HashSet<>();

    @Builder
    public CustomMenu(@NonNull Menu menu, int quantity, @NonNull List<CustomTaste> customTastes) {
        this.menu = menu;
        this.quantity = quantity;
        this.customTastes = new HashSet<>(customTastes);
    }
}
