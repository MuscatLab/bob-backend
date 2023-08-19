package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity(name = "menu")
@Table(schema = "bob", name = "menu", indexes = {@Index(name = "idx_menu", columnList = "id", unique = true)})
public class Menu extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "integer default 0")
    private int price;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "menu_taste",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "taste_id")
    )
    private Set<Taste> tastes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Set<Order> orders = new HashSet<>();

    @Builder
    public Menu(@NonNull String name, int price, @NonNull String imageUrl, @NonNull List<Taste> tastes) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.tastes= new HashSet<>(tastes);
    }

    public Menu addOrder(@NonNull Order order) {
        this.orders.add(order);
        return this;
    }
}
