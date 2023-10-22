package com.muscatlab.bob.dto.member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SignUpInput {
    private String email;

    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }
}
