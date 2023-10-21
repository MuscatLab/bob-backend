package com.muscatlab.bob.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RobotStatus {
    READY,
    PROCEEDING,
    ;

    @JsonCreator
    public static RobotStatus fromString(String value) {
        for (RobotStatus robotStatus : RobotStatus.values()) {
            if (robotStatus.name().equalsIgnoreCase(value)) {
                return robotStatus;
            }
        }
        return null;
    }
}
