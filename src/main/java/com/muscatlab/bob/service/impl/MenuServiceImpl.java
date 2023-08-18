package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.domain.entity.Menu;
import com.muscatlab.bob.domain.entity.Option;
import com.muscatlab.bob.domain.entity.OrderHistory;
import com.muscatlab.bob.domain.entity.Robot;
import com.muscatlab.bob.dto.menu.CreateMenuInput;
import com.muscatlab.bob.dto.menu.MenuOutput;
import com.muscatlab.bob.dto.option.OptionOutput;
import com.muscatlab.bob.repository.MenuRepository;
import com.muscatlab.bob.repository.OrderHistoryRepository;
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
    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    @Transactional
    public MenuOutput create(CreateMenuInput input) {
        List<Robot> robots = this.robotRepository.findAllById(input.getRobotIds());
        Menu menu = this.repository.save(input.toEntity());
        robots.forEach(robot -> this.robotRepository.save(robot.setMenu(menu)));
        return MenuOutput.from(menu, "00:" + this.getExpectedTime(menu), this.getReasons(menu));
    }

    @Override
    public List<MenuOutput> getAll() {
        return this.repository.findAll().stream()
                .map(menu -> MenuOutput.from(menu, "00:" + this.getExpectedTime(menu), this.getReasons(menu)))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<MenuOutput> getAll(String cardUid) {
//        return this.repository.findAll().stream()
//                .map(menu -> MenuOutput.from(menu, "00:" + this.getExpectedTime(menu), this.getReasons(menu), this.getOptionOutputs(menu, cardUid)))
//                .collect(Collectors.toList());
//    }

    private String getExpectedTime(Menu menu) {
        List<Integer> expectedTimes = this.robotRepository.findAllByMenu(menu).stream()
                .map(Robot::getExpectedTime)
                .toList();
        int expectedTime = expectedTimes.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return String.format("%02d", expectedTime);
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

//    private List<OptionOutput> getOptionOutputs(Menu menu, String cardUid) {
//        Set<Option> options = menu.getOptions();
//        List<OrderHistory> orderHistories = this.orderHistoryRepository.findAllByCardUid(cardUid);
//        List<OptionOutput> optionOutputs = new ArrayList<>();
//    }
}
