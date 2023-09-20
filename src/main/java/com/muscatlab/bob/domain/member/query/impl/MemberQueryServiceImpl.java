package com.muscatlab.bob.domain.member.query.impl;

import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.domain.member.query.MemberQueryService;
import com.muscatlab.bob.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public Member getByEmail(String email) {
        return this.memberRepository.findByEmail(email)
                .orElse(null);
    }
}
