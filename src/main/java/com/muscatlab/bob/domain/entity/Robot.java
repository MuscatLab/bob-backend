package com.muscatlab.bob.domain.entity;

import com.muscatlab.bob.common.constant.RobotStatus;
import com.muscatlab.bob.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "robot")
@Table(schema = "bob", name = "robot", indexes = {@Index(name = "idx_robot", columnList = "id", unique = true)})
public class Robot extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "status", nullable = false)
    private RobotStatus status;

    @Builder
    public Robot(@NonNull String name, @NonNull RobotStatus status, Menu menu) {
        this.name = name;
        this.status = status;
        this.menu = menu;
    }

    public Robot updateStatus(RobotStatus status) {
        this.status = status;
        return this;
    }

    public Integer getExpectedTime() {
        if (this.status == RobotStatus.PROCEEDING) {
            return 5;
        }

        return 0;
    }

    public String getReason() {
        if (this.status == RobotStatus.PROCEEDING) {
            return name.split(" 로봇")[0];
        }

        return null;
    }

    public Robot setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }
}
