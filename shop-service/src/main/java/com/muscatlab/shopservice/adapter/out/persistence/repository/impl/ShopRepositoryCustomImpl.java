package com.muscatlab.shopservice.adapter.out.persistence.repository.impl;

import com.muscatlab.shopservice.adapter.out.persistence.repository.ShopRepositoryCustom;
import com.muscatlab.shopservice.application.domain.model.QMenu;
import com.muscatlab.shopservice.application.domain.model.Shop;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.muscatlab.shopservice.application.domain.model.QShop.shop;

@RequiredArgsConstructor
public class ShopRepositoryCustomImpl implements ShopRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Optional<Shop> findByName(String name) {
        return Optional.ofNullable(query.selectFrom(shop)
                .leftJoin(shop.menus, QMenu.menu)
                .where(shop.name.eq(name))
                .fetchOne());
    }

    @Override
    public List<Shop> findAll() {
        return query.selectFrom(shop)
                .leftJoin(shop.menus, QMenu.menu)
                .fetch();
    }
}
