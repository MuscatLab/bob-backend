package com.muscatlab.bob.domain.menu.command.impl;

import com.muscatlab.bob.domain.menu.command.MenuCommandService;
import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuCommandServiceImpl implements MenuCommandService {
    private final MenuRepository menuRepository;

    @Override
    public Menu create(Menu menu) {
        return this.menuRepository.save(menu);
    }
}
