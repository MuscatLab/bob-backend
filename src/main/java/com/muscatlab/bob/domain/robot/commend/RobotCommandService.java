package com.muscatlab.bob.domain.robot.commend;

import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.robot.entity.Robot;

import java.util.List;

public interface RobotCommandService {
    Robot create(Robot robot);

    void updateInitialRobotStatus();

    List<Robot> bulkSetMenu(List<Robot> robots, Menu menu);
}
