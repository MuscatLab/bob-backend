package com.muscatlab.shopservice.adapter.out.persistence.repository;

import com.muscatlab.shopservice.application.domain.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShopRepository extends JpaRepository<Shop, UUID>, ShopRepositoryCustom {
}
