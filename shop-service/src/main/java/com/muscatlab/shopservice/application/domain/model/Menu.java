package com.muscatlab.shopservice.application.domain.model;

import com.muscatlab.shopservice.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@Table(name = "menu", indexes = {@Index(name = "idx_menu_id", columnList = "id", unique = true)})
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Menu extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Builder
    public Menu(@NonNull String name, String description, @NonNull int price, @NonNull Shop shop) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.shop = shop;
    }
}
