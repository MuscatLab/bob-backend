package com.muscatlab.bob.domain.orderStatusHistory.command;

import com.muscatlab.bob.domain.orderStatusHistory.entity.OrderStatusHistory;

public interface OrderStatusHistoryCommandService {
    OrderStatusHistory createWithStatusMessage(String statusMessage);
}
