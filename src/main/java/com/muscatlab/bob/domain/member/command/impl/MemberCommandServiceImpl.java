package com.muscatlab.bob.domain.member.command.impl;

import com.muscatlab.bob.domain.member.command.MemberCommandService;
import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    @Override
    public Member create(Member member) {
        return this.memberRepository.save(member);
    }
}
