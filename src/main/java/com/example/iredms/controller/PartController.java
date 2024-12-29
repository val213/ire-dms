package com.example.iredms.controller;


import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.service.PartService;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/part")
public class PartController {
    @Autowired
    PartService partService;

    @PostMapping("/create")
    public BaseResponse<PartViewDTO> create(@RequestBody PartCreateDTO partCreateDTO) {
        return ResultUtils.success(partService.CreatePart(partCreateDTO));
    }

}
