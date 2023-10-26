package com.muscatlab.memberservice.application.domain.service;

import com.muscatlab.memberservice.application.domain.model.Member;
import com.muscatlab.memberservice.application.port.in.MemberQueryUseCase;
import com.muscatlab.memberservice.application.port.out.LoadMemberPort;
import com.muscatlab.memberservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService implements MemberQueryUseCase {
    private final LoadMemberPort loadMemberPort;

    @Override
    public Member loadMemberBy(UUID id) {
        return loadMemberPort.loadMemberBy(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    @Override
    public Member loadMemberBy(String email) {
        return loadMemberPort.loadMemberBy(email)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
}
