package com.muscatlab.memberservice.adapter.in.web.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MemberResponse(
        UUID id,
        String email,
        String name
) {
}
