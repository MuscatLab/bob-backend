package com.muscatlab.bob.repository.impl;

import com.muscatlab.bob.domain.order.entity.Order;;
import com.muscatlab.bob.domain.customMenu.entity.QCustomMenu;
import com.muscatlab.bob.domain.member.entity.QMember;
import com.muscatlab.bob.domain.orderStatusHistory.entity.QOrderStatusHistory;
import com.muscatlab.bob.repository.OrderRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import static com.muscatlab.bob.domain.order.entity.QOrder.order;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory query;

    // TODO: change select object to dto
    @Override
    public List<Order> findByMemberId(UUID memberId) {
        return query.selectFrom(order)
                .leftJoin(QCustomMenu.customMenu).fetchJoin()
                .leftJoin(QMember.member).fetchJoin()
                .leftJoin(QOrderStatusHistory.orderStatusHistory)
                .where(order.member.id.eq(memberId))
                .fetch();
    }
}
