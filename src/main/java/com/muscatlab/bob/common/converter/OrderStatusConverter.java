package com.muscatlab.bob.common.converter;

import com.muscatlab.bob.common.constant.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OrderStatus attribute) {
        return attribute.ordinal();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer code) {
        return OrderStatus.values()[code];
    }
}
