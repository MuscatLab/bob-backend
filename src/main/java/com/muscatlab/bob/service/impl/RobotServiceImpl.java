package com.muscatlab.bob.service.impl;

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
    private final RobotRepository repository;

    @Override
    @Transactional
    public RobotOutput create(CreateRobotInput input) {
        return RobotOutput.from(this.repository.save(input.toEntity()));
    }

    @Override
    public List<RobotOutput> getAll() {
        return this.repository.findAll().stream()
                .map(RobotOutput::from)
                .collect(Collectors.toList());
    }
}
