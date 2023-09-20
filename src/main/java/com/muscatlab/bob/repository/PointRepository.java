package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.pointAmount.entity.PointAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PointRepository extends JpaRepository<PointAmount, UUID> {
}
