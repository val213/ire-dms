package com.example.iredms.service;

import com.example.iredms.dto.OrderQueryDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderViewDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public OrderViewDTO CreateOrder(OrderViewDTO orderViewDTO);
    public List<OrderViewDTO> queryOrderList(OrderQueryDTO orderQueryDTO);
    public int deleteOrder(OrderViewDTO orderViewDTO);
    public OrderViewDTO updateOrder(OrderUpdateDTO orderUpdateDTO);
    public Map<String,Long> count();
}