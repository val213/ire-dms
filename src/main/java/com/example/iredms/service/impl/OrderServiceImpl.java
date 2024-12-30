package com.example.iredms.service.impl;

import com.example.iredms.service.OrderService;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.OrderDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderListViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
     OrderDelegator orderDelegator;



    @Override
    public OrderViewDTO CreateOrder(OrderViewDTO orderViewDTO) {
        OrderCreateDTO order = new OrderCreateDTO();
        //名称
        if (orderViewDTO.getName() != null && !orderViewDTO.getName().isEmpty()) {
            order.setName(orderViewDTO.getName());
        }
        //类型
        order.setType(orderViewDTO.getType());
        //订单时间
        order.setOrderDate(orderViewDTO.getOrderDate());
        //数量
        if (orderViewDTO.getQuantity() != null && !orderViewDTO.getQuantity().isEmpty()) {
            order.setQuantity(orderViewDTO.getQuantity());
        }
        return orderDelegator.create(order);
    }

    @Override
    public OrderListViewDTO queryOrderList() {
        return null;
    }

    @Override
    public void deleteOrder(int orderId) {

    }

    @Override
    public OrderViewDTO updateOrder() {
        return null;
    }
}
