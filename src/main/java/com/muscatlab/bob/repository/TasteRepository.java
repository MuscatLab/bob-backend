package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.taste.entity.Taste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TasteRepository extends JpaRepository<Taste, UUID> {
}
