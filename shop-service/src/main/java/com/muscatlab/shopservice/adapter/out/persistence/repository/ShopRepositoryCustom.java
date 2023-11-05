package com.muscatlab.shopservice.adapter.out.persistence.repository;

import com.muscatlab.shopservice.application.domain.model.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopRepositoryCustom {
    Optional<Shop> findByName(String name);

    List<Shop> findAll();
}
