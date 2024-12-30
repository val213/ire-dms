package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.service.AttributeService;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    AttributeService attributeService;

    @PostMapping("/get/{id}")
    public BaseResponse<ArrayList<EXADefinitionViewDTO>> getAttributesById(@PathVariable String id) {
        return ResultUtils.success(attributeService.getClassificationAttribute(id));

    };
}
