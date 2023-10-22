package com.muscatlab.bob.domain.member.query;

import com.muscatlab.bob.domain.member.entity.Member;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface MemberQueryService {
    @Nullable
    Member getByEmail(String email);

    @Nullable
    Member getById(UUID id);
}
