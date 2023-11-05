package com.muscatlab.shopservice.adapter.out.persistence.repository.impl;

import com.muscatlab.shopservice.adapter.out.persistence.repository.MenuRepositoryCustom;
import com.muscatlab.shopservice.application.domain.model.Menu;
import com.muscatlab.shopservice.application.domain.model.QShop;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.muscatlab.shopservice.application.domain.model.QMenu.menu;


@RequiredArgsConstructor
public class MenuRepositoryCustomImpl implements MenuRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Optional<Menu> findByShopIdAndName(UUID shopId, String name) {
        return Optional.ofNullable(query.selectFrom(menu)
                .leftJoin(menu.shop, QShop.shop).fetchJoin()
                .where(menu.shop.id.eq(shopId), menu.name.eq(name))
                .fetchOne());
    }

    @Override
    public List<Menu> findByShopId(UUID shopId) {
        return query.selectFrom(menu).distinct()
                .leftJoin(menu.shop, QShop.shop).fetchJoin()
                .where(menu.shop.id.eq(shopId))
                .fetch();
    }
}
