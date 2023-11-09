package com.muscatlab.shopservice.application.port.out;

import com.muscatlab.shopservice.application.domain.model.Shop;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface LoadShopPort {
    Optional<Shop> loadShopByName(String name);

    Optional<Shop> loadShopById(UUID id);

    Set<Shop> loadShopByLocation(String location);
}
