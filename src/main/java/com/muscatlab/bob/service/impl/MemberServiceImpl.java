package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.domain.member.query.MemberQueryService;
import com.muscatlab.bob.dto.member.MemberOutput;
import com.muscatlab.bob.dto.member.SignInInput;
import com.muscatlab.bob.dto.member.SignUpInput;
import com.muscatlab.bob.repository.MemberRepository;
import com.muscatlab.bob.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberQueryService memberQueryServiceImpl;

    @Override
    @Transactional
    public MemberOutput signUp(SignUpInput input) {
        Member member = this.memberQueryServiceImpl.getByEmail(input.getEmail());
        if (Objects.nonNull(member)) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "이미 가입된 이메일입니다.");
        }

        return MemberOutput.from(this.memberRepository.save(input.toEntity()));
    }

    @Override
    @Transactional
    public MemberOutput signIn(SignInInput input) {
        Member member = this.memberQueryServiceImpl.getByEmail(input.getEmail());
        if (Objects.isNull(member)) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "가입되지 않은 이메일입니다.");
        }

        if (!member.validate(input.getPassword())) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "비밀번호가 일치하지 않습니다.");
        }

        return MemberOutput.from(member);
    }
}
