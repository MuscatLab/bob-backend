package com.muscatlab.memberservice.adapter.out.persistence.repository.impl;

import com.muscatlab.memberservice.adapter.out.persistence.repository.MemberRepositoryCustom;
import com.muscatlab.memberservice.application.domain.model.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.muscatlab.memberservice.application.domain.model.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(query.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne());
    }
}
