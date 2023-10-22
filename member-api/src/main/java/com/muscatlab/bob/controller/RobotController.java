package com.muscatlab.bob.controller;

import com.muscatlab.bob.dto.robot.CreateRobotInput;
import com.muscatlab.bob.dto.robot.RobotOutput;
import com.muscatlab.bob.service.RobotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "로봇", description = "로봇 관련 API")
@RestController
@RequestMapping("/robots")
@RequiredArgsConstructor
public class RobotController {
    private final RobotService service;

    @Operation(summary = "로봇 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RobotOutput create(@RequestBody @Valid CreateRobotInput input) {
        return this.service.create(input);
    }

    @Operation(summary = "로봇 전체 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RobotOutput> getAll() {
        return this.service.getAll();
    }
}
