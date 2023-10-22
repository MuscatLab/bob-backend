package com.muscatlab.bob.common.converter;

import com.muscatlab.bob.common.constant.ReturnAmountType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReturnAmountTypeConverter implements AttributeConverter<ReturnAmountType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReturnAmountType attribute) {
        return attribute.ordinal();
    }

    @Override
    public ReturnAmountType convertToEntityAttribute(Integer code) {
        return ReturnAmountType.values()[code];
    }
}
