package com.muscatlab.memberservice.adapter.out.persistence.repository;

import com.muscatlab.memberservice.application.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID>, MemberRepositoryCustom {
}
