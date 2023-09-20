package com.muscatlab.bob.domain.robot.commend.impl;

import com.muscatlab.bob.common.constant.RobotStatus;
import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.robot.commend.RobotCommandService;
import com.muscatlab.bob.domain.robot.entity.Robot;
import com.muscatlab.bob.repository.RobotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RobotCommandServiceImpl implements RobotCommandService {
    private final RobotRepository robotRepository;

    @Override
    public void updateInitialRobotStatus() {
        Robot robot = this.robotRepository.findByNameLike("%떡%");
        this.robotRepository.save(robot.updateStatus(RobotStatus.PROCEEDING));
    }

    @Override
    public List<Robot> bulkSetMenu(List<Robot> robots, Menu menu) {
        robots.forEach(robot -> robot.setMenu(menu));
        return this.robotRepository.saveAll(robots);
    }
}
