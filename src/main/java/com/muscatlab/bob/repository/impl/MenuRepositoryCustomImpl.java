package com.muscatlab.bob.repository.impl;

import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.entity.QOrder;
import com.muscatlab.bob.domain.entity.QTaste;
import com.muscatlab.bob.repository.MenuRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.muscatlab.bob.domain.entity.QMenu.menu;

@RequiredArgsConstructor
public class MenuRepositoryCustomImpl implements MenuRepositoryCustom {
    private final JPAQueryFactory query;

    // TODO: change select object to dto
    @Override
    public Optional<Menu> findByName(String name) {
        return Optional.ofNullable(query.selectFrom(menu)
                .leftJoin(QTaste.taste)
                .leftJoin(QOrder.order)
                .where(menu.name.eq(name))
                .fetchOne());
    }

    // TODO: change select object to dto
    
    @Override
    public List<Menu> findAll() {
        return query.selectFrom(menu)
                .leftJoin(QTaste.taste)
                .leftJoin(QOrder.order)
                .fetch();
    }
}
