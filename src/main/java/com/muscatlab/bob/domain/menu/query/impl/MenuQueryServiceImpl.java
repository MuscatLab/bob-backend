package com.muscatlab.bob.domain.menu.query.impl;

import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.menu.query.MenuQueryService;
import com.muscatlab.bob.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuQueryServiceImpl implements MenuQueryService {
    private final MenuRepository menuRepository;

    @Override
    public List<Menu> getAll() {
        return this.menuRepository.findAll();
    }
}
