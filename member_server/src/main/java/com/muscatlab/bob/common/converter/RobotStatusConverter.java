package com.muscatlab.bob.common.converter;

import com.muscatlab.bob.common.constant.RobotStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RobotStatusConverter implements AttributeConverter<RobotStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RobotStatus attribute) {
        return attribute.ordinal();
    }

    @Override
    public RobotStatus convertToEntityAttribute(Integer code) {
        return RobotStatus.values()[code];
    }
}
