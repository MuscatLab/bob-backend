package com.muscatlab.memberservice.application.port.in;

import com.muscatlab.memberservice.application.domain.model.Member;

import java.util.UUID;

public interface MemberQueryUseCase {
    Member loadMemberBy(UUID id);

    Member loadMemberBy(String email);
}
