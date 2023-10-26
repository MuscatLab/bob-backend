package com.muscatlab.memberservice.application.port.out;

import com.muscatlab.memberservice.application.domain.model.Member;

public interface CreateMemberPort {
    Member createMember(Member member);
}
