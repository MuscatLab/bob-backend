package com.muscatlab.bob.repository;

import com.muscatlab.bob.domain.robot.entity.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RobotRepository extends JpaRepository<Robot, UUID>, RobotRepositoryCustom {
}
