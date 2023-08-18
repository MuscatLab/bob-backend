package com.muscatlab.bob.dto.robot;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.common.constant.RobotStatus;
import com.muscatlab.bob.domain.entity.Robot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateRobotInput {
    @NotEmpty
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    public Robot toEntity() {
        return Robot.builder()
                .name(name)
                .status(RobotStatus.READY)
                .build();
    }
}
