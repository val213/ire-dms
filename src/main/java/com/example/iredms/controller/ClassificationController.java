package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.service.ClassificationService;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartViewDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/Classification")
public class ClassificationController {

    @Autowired
    ClassificationService classificationService;

    @PostMapping("/query")
    public BaseResponse<ArrayList<ClassificationNodeViewDTO>> query(){
        return ResultUtils.success(classificationService.getClassificationList());
    };
}
