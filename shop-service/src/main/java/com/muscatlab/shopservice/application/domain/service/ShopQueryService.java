package com.muscatlab.shopservice.application.domain.service;

import com.muscatlab.shopservice.application.domain.model.Shop;
import com.muscatlab.shopservice.application.port.in.ShopQueryUseCase;
import com.muscatlab.shopservice.application.port.out.LoadShopPort;
import com.muscatlab.shopservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@UseCase
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopQueryService implements ShopQueryUseCase {
    private final LoadShopPort loadShopPort;

    @Override
    public Shop loadShopByName(String name) {
        return loadShopPort.loadShopByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Shop not found"));
    }

    @Override
    public Set<Shop> loadShopByLocation(String location) {
        return loadShopPort.loadShopByLocation(location);
    }

    @Override
    public Shop loadShopById(UUID id) {
        return loadShopPort.loadShopById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shop not found"));
    }
}
