package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.menu.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepositoryCustom {
    Optional<Menu> findByName(String name);

    List<Menu> findAll();
}
