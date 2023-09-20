package com.muscatlab.bob.domain.customMenu.command.impl;

import com.muscatlab.bob.domain.customMenu.command.CustomMenuCommandService;
import com.muscatlab.bob.domain.customMenu.entity.CustomMenu;
import com.muscatlab.bob.repository.CustomMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomMenuCommandServiceImpl implements CustomMenuCommandService {
    private final CustomMenuRepository customMenuRepository;


    @Override
    public CustomMenu create(CustomMenu customMenu) {
        return this.customMenuRepository.save(customMenu);
    }
}
