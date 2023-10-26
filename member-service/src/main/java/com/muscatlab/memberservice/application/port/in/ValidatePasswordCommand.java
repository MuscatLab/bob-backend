package com.muscatlab.memberservice.application.port.in;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record ValidatePasswordCommand(
        @NonNull
        String email,
        @NonNull
        String password
) {
}
