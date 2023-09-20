package com.muscatlab.bob.repository;

import com.muscatlab.bob.customMenu.CustomMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomMenuRepository extends JpaRepository<CustomMenu, UUID> {
}
