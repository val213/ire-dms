package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.dto.OrderQueryDTO;
import com.example.iredms.service.OrderService;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public BaseResponse<OrderViewDTO> create(@RequestBody OrderViewDTO orderViewDTO) {
        return ResultUtils.success(orderService.CreateOrder(orderViewDTO));
    }

    @PostMapping("/query")
    public  BaseResponse<List<OrderViewDTO>> query(@RequestBody OrderQueryDTO orderQueryDTO) {
        return ResultUtils.success(orderService.queryOrderList(orderQueryDTO));
    }

    @PostMapping("/delete")
    public BaseResponse<Integer> delete(@RequestBody OrderViewDTO orderViewDTO) {
        return ResultUtils.success(orderService.deleteOrder(orderViewDTO));
    }

    @PostMapping("/update")
    public BaseResponse<OrderViewDTO> update(@RequestBody OrderUpdateDTO orderUpdateDTO) {
        return ResultUtils.success(orderService.updateOrder(orderUpdateDTO));
    }

    @PostMapping("/count")
    public BaseResponse<Map<String,Long>> count(){
        return ResultUtils.success(orderService.count());
    }
}
