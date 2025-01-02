package com.example.iredms.controller;


import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.dto.PartQueryDTO;
import com.example.iredms.service.PartService;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/part")
public class PartController {
    @Autowired
    PartService partService;

    @PostMapping("/create")
    public BaseResponse<PartViewDTO> create(@RequestBody PartCreateDTO partCreateDTO) {
        return ResultUtils.success(partService.CreatePart(partCreateDTO));
    }

    @PostMapping("/query")
    public  BaseResponse<List<PartViewDTO>> query(@RequestBody PartQueryDTO partQueryDTO) {
        return ResultUtils.success(partService.queryPartList(partQueryDTO));
    }

    @PostMapping("/delete")
    public BaseResponse<Integer> delete(@RequestBody PartViewDTO partViewDTO) {
        return ResultUtils.success(partService.deletePart(partViewDTO));
    }

    @PostMapping("/update")
    public BaseResponse<PartViewDTO> update(@RequestBody PartUpdateDTO partUpdateDTO) {
        return ResultUtils.success(partService.updatePart(partUpdateDTO));
    }
    @PostMapping("/count")
    public BaseResponse<Map<String,Long>> count(){
        return ResultUtils.success(partService.count());
    }
}
