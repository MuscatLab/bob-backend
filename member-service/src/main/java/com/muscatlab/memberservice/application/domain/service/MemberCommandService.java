package com.muscatlab.memberservice.application.domain.service;

import com.muscatlab.memberservice.application.domain.model.Member;
import com.muscatlab.memberservice.application.port.in.CreateMemberCommand;
import com.muscatlab.memberservice.application.port.in.MemberCommandUseCase;
import com.muscatlab.memberservice.application.port.in.ValidatePasswordCommand;
import com.muscatlab.memberservice.application.port.out.CreateMemberPort;
import com.muscatlab.memberservice.application.port.out.LoadMemberPort;
import com.muscatlab.memberservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@UseCase
@Transactional
public class MemberCommandService implements MemberCommandUseCase {
    private final LoadMemberPort loadMemberPort;
    private final CreateMemberPort createMemberPort;

    @Override
    public Member createMember(CreateMemberCommand command) {
        Optional<Member> existsMember = loadMemberPort.loadMemberBy(command.email());
        if (existsMember.isPresent()) {
            throw new IllegalArgumentException("Member already exists");
        }
        return createMemberPort.createMember(command.toEntity());
    }

    @Override
    public boolean validatePassword(ValidatePasswordCommand command) {
        Member member = loadMemberPort.loadMemberBy(command.email())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return member.validatePassword(command.password());
    }
}
