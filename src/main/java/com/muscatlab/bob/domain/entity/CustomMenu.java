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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "custom_menu_custom_option",
            joinColumns = @JoinColumn(name = "custom_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "custon_option_id"))
    private Set<CustomOption> customOptions = new HashSet<>();

    @Builder
    public CustomMenu(@NonNull Menu menu, @NonNull List<CustomOption> customOptions) {
        this.menu = menu;
        this.customOptions = new HashSet<>(customOptions);
    }
}
