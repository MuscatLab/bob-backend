package com.muscatlab.shopservice.application.port.in;

import com.muscatlab.shopservice.application.domain.model.Shop;

import java.util.Set;
import java.util.UUID;

public interface ShopQueryUseCase {
    Shop loadShopByName(String name);

    Set<Shop> loadShopByLocation(String location);

    Shop loadShopById(UUID id);
}
