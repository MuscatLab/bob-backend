package com.muscatlab.bob.domain.robot.query.impl;

import com.muscatlab.bob.domain.customMenu.entity.CustomMenu;
import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.robot.entity.Robot;
import com.muscatlab.bob.domain.robot.query.RobotQueryService;
import com.muscatlab.bob.repository.RobotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RobotQueryServiceImpl implements RobotQueryService {
    private final RobotRepository robotRepository;

    @Override
    public String getExpectedTime(Menu menu) {
        List<Integer> expectedTimes = this.robotRepository.findAllByMenu(menu).stream()
                .map(Robot::getExpectedTime)
                .toList();
        int expectedTime = expectedTimes.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return String.format("%02d", expectedTime + menu.getDefaultExpectedTime());
    }

    @Override
    public String getReasons(Menu menu) {
        List<String> reasons = this.robotRepository.findAllByMenu(menu).stream()
                .map(Robot::getReason)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (reasons.isEmpty()) {
            return null;
        }

        return String.join(", ", reasons);
    }

    @Override
    public List<Robot> getAllByIds(List<UUID> ids) {
        return this.robotRepository.findAllById(ids);
    }

    @Override
    public int getReturnAmount(CustomMenu customMenu) {
        return customMenu.getMenu().getPrice() / 100 * (100 - customMenu.getQuantity());
    }

    @Override
    public List<Robot> getAll() {
        return this.robotRepository.findAll();
    }
}
