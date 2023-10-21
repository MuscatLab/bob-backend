package com.muscatlab.bob.domain.orderHistory.command;

import com.muscatlab.bob.domain.customMenu.entity.CustomMenu;
import com.muscatlab.bob.domain.member.entity.Member;
import com.muscatlab.bob.domain.orderHistory.entity.OrderHistory;

public interface OrderHistoryCommandService {
    OrderHistory create(CustomMenu customMenu, Member member);
}
