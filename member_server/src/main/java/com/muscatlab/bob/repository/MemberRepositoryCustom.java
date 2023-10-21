package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
}
