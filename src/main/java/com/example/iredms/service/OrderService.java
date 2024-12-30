package com.example.iredms.service;

import com.example.iredms.dto.OrderQueryDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderListViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderViewDTO;

import java.util.List;

public interface OrderService {
    public OrderViewDTO CreateOrder(OrderViewDTO orderViewDTO);
    public List<OrderViewDTO> queryOrderList(OrderQueryDTO orderQueryDTO);
    public int deleteOrder(OrderViewDTO orderViewDTO);
    public OrderViewDTO updateOrder(OrderUpdateDTO orderUpdateDTO);
}