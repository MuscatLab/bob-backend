package com.muscatlab.bob.domain.robot.query;

import com.muscatlab.bob.customMenu.CustomMenu;
import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.robot.entity.Robot;

import java.util.List;
import java.util.UUID;

public interface RobotQueryService {
    String getExpectedTime(Menu menu);

    String getReasons(Menu menu);

    List<Robot> getAllByIds(List<UUID> ids);

    int getReturnAmount(CustomMenu customMenu);

    List<Robot> getAll();
}
