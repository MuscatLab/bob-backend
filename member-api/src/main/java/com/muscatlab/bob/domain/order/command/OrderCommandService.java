package com.muscatlab.bob.domain.order.command;

import com.muscatlab.bob.domain.order.command.dto.CreateOrderParam;
import com.muscatlab.bob.domain.order.entity.Order;

public interface OrderCommandService {
    Order create(CreateOrderParam param);
}
