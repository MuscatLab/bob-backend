package com.muscatlab.bob.dto.member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberOutput {
    private UUID id;

    private String email;

    private int donationAmount;

    private int pointAmount;

    private LocalDateTime createdDate;

    public static MemberOutput from(Member entity) {
        return new MemberOutput()
                .setId(entity.getId())
                .setEmail(entity.getEmail())
                .setDonationAmount(Objects.isNull(entity.getDonation()) ? 0 : entity.getDonation().getAmount())
                .setPointAmount(Objects.isNull(entity.getPointAmount()) ? 0 : entity.getPointAmount().getAmount())
                .setCreatedDate(entity.getCreatedDate());
    }
}
