package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.menu.CreateMenuInput;
import com.muscatlab.bob.dto.menu.MenuOutput;

import java.util.List;

public interface MenuService {
    MenuOutput create(CreateMenuInput input);

    List<MenuOutput> getAll();

//    List<MenuOutput> getAll(String cardUid);
}
