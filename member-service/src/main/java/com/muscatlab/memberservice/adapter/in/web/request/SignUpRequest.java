package com.muscatlab.memberservice.adapter.in.web.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.memberservice.application.port.in.CreateMemberCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SignUpRequest(
        @Email
        @NotNull(message = "Email is required")
        String email,
        @NotNull(message = "Name is required")
        String name,
        @NotNull(message = "Password is required")
        String password
) {

    public CreateMemberCommand toCommand() {
        return CreateMemberCommand.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }
}
