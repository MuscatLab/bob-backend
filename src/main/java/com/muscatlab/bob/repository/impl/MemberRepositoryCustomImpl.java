package com.muscatlab.bob.repository.impl;

import com.muscatlab.bob.domain.entity.*;
import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.repository.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.muscatlab.bob.domain.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory query;

    // TODO: change select object to dto
    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(query.selectFrom(member)
                .leftJoin(QDonation.donation).fetchJoin()
                .leftJoin(QPointAmount.pointAmount).fetchJoin()
                .leftJoin(QOrderHistory.orderHistory)
                .leftJoin(QOrder.order)
                .where(member.email.eq(email))
                .fetchOne());
    }
}
