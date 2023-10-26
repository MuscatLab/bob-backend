package com.muscatlab.memberservice.application.port.in;

import com.muscatlab.memberservice.application.domain.model.Member;

public interface MemberCommandUseCase {
    Member createMember(CreateMemberCommand command);

    boolean validatePassword(ValidatePasswordCommand command);
}
