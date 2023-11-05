package com.muscatlab.shopservice.adapter.out.persistence.repository;

import com.muscatlab.shopservice.application.domain.model.Menu;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepositoryCustom {
    Optional<Menu> findByShopIdAndName(UUID shopId, String name);

    List<Menu> findByShopId(UUID shopId);
}
