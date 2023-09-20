package com.muscatlab.bob.domain.menu.query;

import com.muscatlab.bob.domain.menu.entity.Menu;

import java.util.List;

public interface MenuQueryService {
    List<Menu> getAll();
}
