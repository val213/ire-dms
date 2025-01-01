package com.example.iredms.service;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DesignService {
    Boolean create(DesignBlueprintCreateDTO designBlueprintCreateDTO);
    List<DesignBlueprintQueryViewDTO> query(QueryRequestVo queryRequestVo);
    Boolean update(DesignBlueprintUpdateDTO designBlueprintUpdateDTO);
    int delete(String id);
    DesignBlueprintQueryViewDTO detail(String id);
}