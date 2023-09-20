package com.muscatlab.bob.service.impl;

import com.muscatlab.bob.domain.robot.commend.RobotCommandService;
import com.muscatlab.bob.domain.robot.query.RobotQueryService;
import com.muscatlab.bob.dto.robot.CreateRobotInput;
import com.muscatlab.bob.dto.robot.RobotOutput;
import com.muscatlab.bob.repository.RobotRepository;
import com.muscatlab.bob.service.RobotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RobotServiceImpl implements RobotService {
    private final RobotQueryService robotQueryService;
    private final RobotCommandService robotCommandService;

    @Override
    @Transactional
    public RobotOutput create(CreateRobotInput input) {
        return RobotOutput.from(this.robotCommandService.create(input.toEntity()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RobotOutput> getAll() {
        return this.robotQueryService.getAll().stream()
                .map(RobotOutput::from)
                .collect(Collectors.toList());
    }
}
