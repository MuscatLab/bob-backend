package com.muscatlab.bob.domain.menu.query;

import com.muscatlab.bob.domain.menu.entity.Menu;
import org.springframework.lang.Nullable;

import java.util.List;

public interface MenuQueryService {
    List<Menu> getAll();

    @Nullable
    Menu getByName(String name);
}
