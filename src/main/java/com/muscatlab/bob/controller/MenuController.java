package com.muscatlab.bob.controller;

import com.muscatlab.bob.dto.menu.CreateMenuInput;
import com.muscatlab.bob.dto.menu.MenuOutput;
import com.muscatlab.bob.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "메뉴", description = "메뉴 관련 API")
@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService service;

    @Operation(summary = "메뉴 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuOutput create(@RequestBody @Valid CreateMenuInput input) {
        return this.service.create(input);
    }

    @Operation(summary = "메뉴 전체 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuOutput> getAll() {
        return this.service.getAll();
    }
}
