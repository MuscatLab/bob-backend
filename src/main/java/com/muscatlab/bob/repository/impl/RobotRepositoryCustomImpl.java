package com.muscatlab.bob.repository.impl;

import com.muscatlab.bob.domain.menu.entity.Menu;
import com.muscatlab.bob.domain.menu.entity.QMenu;
import com.muscatlab.bob.domain.robot.entity.Robot;
import com.muscatlab.bob.repository.RobotRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import static com.muscatlab.bob.domain.robot.entity.QRobot.robot;

@RequiredArgsConstructor
public class RobotRepositoryCustomImpl implements RobotRepositoryCustom {
    private final JPAQueryFactory query;

    // TODO: change select object to dto
    @Override
    public List<Robot> findAllByMenu(Menu menu) {
        return query.selectFrom(robot)
                .leftJoin(QMenu.menu).fetchJoin()
                .where(robot.menu.eq(menu))
                .fetch();
    }

    // TODO: change select object to dto
    @Override
    public Robot findByNameLike(String name) {
        return query.selectFrom(robot)
                .leftJoin(QMenu.menu).fetchJoin()
                .where(robot.name.like(name))
                .fetchOne();
    }

    // TODO: change select object to dto
    @Override
    public List<Robot> findAllByIds(List<UUID> ids) {
        return query.selectFrom(robot)
                .leftJoin(QMenu.menu).fetchJoin()
                .where(robot.id.in(ids))
                .fetch();
    }

    @Override
    public List<Robot> findAll() {
        return query.selectFrom(robot)
                .leftJoin(QMenu.menu).fetchJoin()
                .fetch();
    }
}
