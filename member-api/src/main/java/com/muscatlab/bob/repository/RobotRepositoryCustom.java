package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.robot.entity.Robot;

import java.util.List;
import java.util.UUID;

public interface RobotRepositoryCustom {
    List<Robot> findAllByMenu(Menu menu);

    Robot findByNameLike(String name);

    List<Robot> findAllByIds(List<UUID> ids);

    List<Robot> findAll();
}
