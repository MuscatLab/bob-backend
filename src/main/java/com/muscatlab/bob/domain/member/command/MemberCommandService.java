package com.muscatlab.bob.domain.member.command;

import com.muscatlab.bob.domain.member.entity.Member;

public interface MemberCommandService {
    Member create(Member member);
}
