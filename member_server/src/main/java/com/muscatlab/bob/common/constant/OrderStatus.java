package com.muscatlab.bob.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatus {
    READY,
    PROCEEDING,
    FINISHED,
    ;

    @JsonCreator
    public static OrderStatus from(String value) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().equalsIgnoreCase(value)) {
                return orderStatus;
            }
        }
        return null;
    }
}
