package com.muscatlab.memberservice.adapter.in.web.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.memberservice.application.port.in.ValidatePasswordCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SignInRequest(
        @NotNull(message = "Email is required")
        @Email(message = "Email is invalid")
        String email,
        @NotNull(message = "Password is required")
        String password
) {

    public ValidatePasswordCommand toCommand() {
        return ValidatePasswordCommand.builder()
                .email(email)
                .password(password)
                .build();
    }
}
