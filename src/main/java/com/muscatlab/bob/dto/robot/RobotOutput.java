package com.muscatlab.bob.dto.robot;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.common.constant.RobotStatus;
import com.muscatlab.bob.domain.entity.Robot;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RobotOutput {
    private UUID id;

    private String name;

    private RobotStatus status;

    private List<String> menuNames;

    public static RobotOutput from(Robot robot) {
        return new RobotOutput()
                .setId(robot.getId())
                .setName(robot.getName())
                .setStatus(robot.getStatus())
                .setMenuNames(Objects.isNull(robot.getMenu()) ? new ArrayList<>() : List.of(robot.getMenu().getName()));
    }
}
