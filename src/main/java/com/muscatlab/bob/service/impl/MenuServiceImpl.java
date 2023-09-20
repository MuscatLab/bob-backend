package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.domain.menu.command.MenuCommandService;
import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.menu.query.MenuQueryService;
import com.muscatlab.bob.domain.robot.commend.RobotCommandService;
import com.muscatlab.bob.domain.robot.entity.Robot;
import com.muscatlab.bob.domain.robot.query.RobotQueryService;
import com.muscatlab.bob.dto.menu.CreateMenuInput;
import com.muscatlab.bob.dto.menu.MenuOutput;
import com.muscatlab.bob.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuQueryService menuQueryService;
    private final MenuCommandService menuCommandService;
    private final RobotQueryService robotQueryService;
    private final RobotCommandService robotCommandService;

    @Override
    @Transactional
    public MenuOutput create(CreateMenuInput input) {
        List<Robot> robots = this.robotQueryService.getAllByIds(input.getRobotIds());
        Menu menu = this.menuCommandService.create(input.toEntity());
        this.robotCommandService.bulkSetMenu(robots, menu);
        return MenuOutput.from(menu, this.robotQueryService.getExpectedTime(menu), this.robotQueryService.getReasons(menu));
    }

    @Override
    public List<MenuOutput> getAll() {
        return this.menuQueryService.getAll().stream()
                .map(menu -> MenuOutput.from(menu, this.robotQueryService.getExpectedTime(menu), this.robotQueryService.getReasons(menu)))
                .collect(Collectors.toList());
    }
}
