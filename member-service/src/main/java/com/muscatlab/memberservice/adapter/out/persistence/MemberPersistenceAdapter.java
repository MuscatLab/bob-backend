package com.muscatlab.memberservice.adapter.out.persistence;


import com.muscatlab.memberservice.adapter.out.persistence.repository.MemberRepository;
import com.muscatlab.memberservice.application.domain.model.Member;
import com.muscatlab.memberservice.application.port.out.CreateMemberPort;
import com.muscatlab.memberservice.application.port.out.LoadMemberPort;
import com.muscatlab.memberservice.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort, CreateMemberPort {
    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> loadMemberBy(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Optional<Member> loadMemberBy(UUID id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }
}
