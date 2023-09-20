package com.muscatlab.bob.repository.impl;

import com.muscatlab.bob.domain.card.entity.Card;
import com.muscatlab.bob.domain.entity.QOrder;
import com.muscatlab.bob.domain.entity.QOrderHistory;
import com.muscatlab.bob.repository.CardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.muscatlab.bob.domain.entity.QCard.card;

@RequiredArgsConstructor
public class CardRepositoryCustomImpl implements CardRepositoryCustom {
    private final JPAQueryFactory query;

    // TODO: change select object to dto and optional
    @Override
    public Card findByUid(@NonNull String uid) {
        return query.selectFrom(card)
                .leftJoin(QOrderHistory.orderHistory)
                .leftJoin(QOrder.order)
                .where(card.uid.eq(uid))
                .fetchOne();
    }
}
