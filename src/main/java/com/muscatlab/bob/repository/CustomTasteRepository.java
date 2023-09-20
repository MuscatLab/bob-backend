package com.muscatlab.bob.repository;

import com.muscatlab.bob.customTaste.CustomTaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomTasteRepository extends JpaRepository<CustomTaste, UUID> {
}
