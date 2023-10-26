package com.muscatlab.memberservice.application.port.out;

import com.muscatlab.memberservice.application.domain.model.Member;

import java.util.Optional;
import java.util.UUID;

public interface LoadMemberPort {
    Optional<Member> loadMemberBy(String email);

    Optional<Member> loadMemberBy(UUID id);
}
