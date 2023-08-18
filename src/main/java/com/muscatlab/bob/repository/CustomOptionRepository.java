package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.entity.CustomOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomOptionRepository extends JpaRepository<CustomOption, UUID> {
}
