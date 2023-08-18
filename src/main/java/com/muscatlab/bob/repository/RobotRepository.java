package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.entity.Menu;
import com.muscatlab.bob.domain.entity.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RobotRepository extends JpaRepository<Robot, UUID> {
    List<Robot> findAllByMenu(Menu menu);
}
