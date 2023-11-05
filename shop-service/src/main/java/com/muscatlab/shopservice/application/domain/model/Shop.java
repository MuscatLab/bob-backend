package com.muscatlab.shopservice.application.domain.model;

import com.muscatlab.shopservice.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "shop", indexes = {@Index(name = "idx_shop_id", columnList = "id")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Menu> menus = new HashSet<>();

    @Builder
    public Shop(@NonNull String name, @NonNull String location, String description,
                Set<Menu> menus) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.menus = menus;
    }
}
