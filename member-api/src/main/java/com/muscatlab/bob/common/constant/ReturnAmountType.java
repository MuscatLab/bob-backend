package com.muscatlab.bob.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ReturnAmountType {
    DONATION,
    POINT,
    ;

    @JsonCreator
    public static ReturnAmountType fromString(String value) {
        for (ReturnAmountType returnAmountType : ReturnAmountType.values()) {
            if (returnAmountType.name().equalsIgnoreCase(value)) {
                return returnAmountType;
            }
        }
        return null;
    }
}
