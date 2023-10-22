package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.robot.CreateRobotInput;
import com.muscatlab.bob.dto.robot.RobotOutput;

import java.util.List;

public interface RobotService {
    RobotOutput create(CreateRobotInput input);

    List<RobotOutput> getAll();
}
