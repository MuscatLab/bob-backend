package com.muscatlab.memberservice.adapter.out.persistence.repository;

import com.muscatlab.memberservice.application.domain.model.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
}
