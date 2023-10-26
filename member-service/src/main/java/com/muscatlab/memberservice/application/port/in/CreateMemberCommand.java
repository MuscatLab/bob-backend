package com.muscatlab.memberservice.application.port.in;

import com.muscatlab.memberservice.application.domain.model.Member;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreateMemberCommand(
        @NonNull
        String email,
        @NonNull
        String name,
        @NonNull
        String password
) {

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
