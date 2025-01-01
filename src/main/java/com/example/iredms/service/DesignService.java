package com.example.iredms.service;

import com.example.iredms.utils.CustomFile;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintViewDTO;

import java.util.List;
public interface DesignService {
    Boolean create(DesignBlueprintCreateDTO designBlueprintCreateDTO);
    List<DesignBlueprintViewDTO> query(Long id);
    Boolean update(Long id, DesignBlueprintUpdateDTO designBlueprintUpdateDTO);
    int delete(DeleteByConditionVo deleteByConditionVo);
    DesignBlueprintViewDTO detail(Long id);
    RDMResultVO uploadFile(CustomFile customFile);
}