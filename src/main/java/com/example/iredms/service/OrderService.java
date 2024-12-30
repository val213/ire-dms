package com.example.iredms.service;

import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderListViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderViewDTO;

public interface OrderService {
    public OrderViewDTO CreateOrder(OrderViewDTO orderViewDTO);
    public OrderListViewDTO queryOrderList();
    public void deleteOrder(int orderId);
    public OrderViewDTO updateOrder();
}