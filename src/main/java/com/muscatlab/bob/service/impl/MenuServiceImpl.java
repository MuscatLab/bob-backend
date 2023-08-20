package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.domain.entity.Menu;
import com.muscatlab.bob.domain.entity.Robot;
import com.muscatlab.bob.dto.menu.CreateMenuInput;
import com.muscatlab.bob.dto.menu.MenuOutput;
import com.muscatlab.bob.repository.MenuRepository;
import com.muscatlab.bob.repository.RobotRepository;
import com.muscatlab.bob.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository repository;
    private final RobotRepository robotRepository;

    @Override
    @Transactional
    public MenuOutput create(CreateMenuInput input) {
        List<Robot> robots = this.robotRepository.findAllById(input.getRobotIds());
        Menu menu = this.repository.save(input.toEntity());
        robots.forEach(robot -> this.robotRepository.save(robot.setMenu(menu)));
        return MenuOutput.from(menu, this.getExpectedTime(menu), this.getReasons(menu));
    }

    @Override
    public List<MenuOutput> getAll() {
        return this.repository.findAll().stream()
                .map(menu -> MenuOutput.from(menu, this.getExpectedTime(menu), this.getReasons(menu)))
                .collect(Collectors.toList());
    }

    private String getExpectedTime(Menu menu) {
        List<Integer> expectedTimes = this.robotRepository.findAllByMenu(menu).stream()
                .map(Robot::getExpectedTime)
                .toList();
        int expectedTime = expectedTimes.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return String.format("%02d", expectedTime + menu.getDefaultExpectedTime());
    }

    private String getReasons(Menu menu) {
        List<String> reasons = this.robotRepository.findAllByMenu(menu).stream()
                .map(Robot::getReason)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (reasons.isEmpty()) {
            return null;
        }

        return String.join(", ", reasons);
    }
}
