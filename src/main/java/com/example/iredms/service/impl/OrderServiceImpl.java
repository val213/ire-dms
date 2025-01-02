package com.example.iredms.service.impl;

import com.example.iredms.dto.OrderQueryDTO;
import com.example.iredms.service.OrderService;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.OrderDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderViewDTO;
import com.huawei.innovation.rdm.xdm.delegator.ListAttributeLayoutDelegator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
     OrderDelegator orderDelegator;
    @Autowired
    private ListAttributeLayoutDelegator listAttributeLayoutDelegator;
    @Autowired
    private ProductDelegator productDelegator;


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
    public List<OrderViewDTO> queryOrderList(OrderQueryDTO orderQueryDTO) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        if (orderQueryDTO.getId() != null) {
            queryRequestVo.addCondition("id", ConditionType.EQUAL,orderQueryDTO.getId());
        }
        if (orderQueryDTO.getName() != null && !orderQueryDTO.getName().isEmpty()) {
            queryRequestVo.addCondition("name", ConditionType.LIKE,orderQueryDTO.getName());
        }
        if (orderQueryDTO.getType() != null && !orderQueryDTO.getType().isEmpty()) {
            queryRequestVo.addCondition("type", ConditionType.EQUAL,orderQueryDTO.getType());
        }
        RDMPageVO pageVO = new RDMPageVO(1, Integer.MAX_VALUE);
        List<OrderViewDTO> result;
        try {
            result = orderDelegator.find(queryRequestVo, pageVO);
        } catch (Exception e) {
            return Collections.emptyList();
        }
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result;
    }

    @Override
    public int deleteOrder(OrderViewDTO orderViewDTO) {
        if (orderViewDTO.getId() != null) {
            QueryRequestVo queryRequestVo = new QueryRequestVo();
            queryRequestVo.addCondition("id", ConditionType.EQUAL,orderViewDTO.getId());
            DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
            deleteByConditionVo.setCondition(queryRequestVo);
            return orderDelegator.deleteByCondition(deleteByConditionVo);
        } else return 0;
    }

    @Override
    public OrderViewDTO updateOrder(OrderUpdateDTO orderUpdateDTO) {
        return orderDelegator.update(orderUpdateDTO);
    }

    public Map<String,Long> count() {
        QueryRequestVo queryRequestVo1 = new QueryRequestVo();
        queryRequestVo1.addCondition("type", ConditionType.EQUAL,"Sale");
        long sale = orderDelegator.count(queryRequestVo1);
        QueryRequestVo queryRequestVo2 = new QueryRequestVo();
        queryRequestVo2.addCondition("type", ConditionType.EQUAL,"Purchase");
        long purchase = orderDelegator.count(queryRequestVo2);
        QueryRequestVo queryRequestVo3 = new QueryRequestVo();
        queryRequestVo3.addCondition("type", ConditionType.EQUAL,"Manufacture");
        long manufacture = orderDelegator.count(queryRequestVo3);
        Map<String,Long> result = new java.util.HashMap<>();
        result.put("Sale",sale);
        result.put("Purchase",purchase);
        result.put("Manufacture",manufacture);
        return result;
    }
}
