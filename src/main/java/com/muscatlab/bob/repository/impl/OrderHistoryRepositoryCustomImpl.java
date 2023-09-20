package com.muscatlab.bob.repository.impl;

import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;
import com.muscatlab.bob.domain.entity.QCustomMenu;
import com.muscatlab.bob.repository.OrderHistoryRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.muscatlab.bob.domain.entity.QOrderHistory.orderHistory;

@RequiredArgsConstructor
public class OrderHistoryRepositoryCustomImpl implements OrderHistoryRepositoryCustom {
    private final JPAQueryFactory query;

    // TODO: change select object to dto
    @Override
    public List<OrderHistory> findAllByMemberId(UUID memberId) {
        return query.selectFrom(orderHistory)
                .leftJoin(QCustomMenu.customMenu).fetchJoin()
                .where(orderHistory.member.id.eq(memberId))
                .fetch();
    }

    // TODO: change select object to dto
    @Override
    public Optional<OrderHistory> findByCustomMenuId(UUID id) {
        return Optional.ofNullable(query.selectFrom(orderHistory)
                .leftJoin(QCustomMenu.customMenu).fetchJoin()
                .where(orderHistory.customMenu.id.eq(id))
                .fetchOne());
    }
}
