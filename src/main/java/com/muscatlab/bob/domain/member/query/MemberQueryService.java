package com.muscatlab.bob.domain.member.query;

import com.muscatlab.bob.domain.member.entity.Member;

public interface MemberQueryService {
    Member getByEmail(String email);
}
