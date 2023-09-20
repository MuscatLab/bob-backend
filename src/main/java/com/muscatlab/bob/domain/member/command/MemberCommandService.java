package com.muscatlab.bob.domain.member.command;

import com.muscatlab.bob.domain.member.entity.Member;

public interface MemberCommandService {
    Member create(Member member);

    void addDonation(Member member, int amount);

    void addPoint(Member member, int amount);
}
