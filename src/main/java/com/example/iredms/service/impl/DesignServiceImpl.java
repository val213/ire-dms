package com.example.iredms.service.impl;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.DesignBlueprintDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignServiceImpl {
    DesignBlueprintDelegator designBlueprintDelegator;
    Boolean create(DesignBlueprintCreateDTO designBlueprintCreateDTO) {
        designBlueprintDelegator.create(designBlueprintCreateDTO);
        return null;
    }

    List<DesignBlueprintQueryViewDTO> query(QueryRequestVo queryRequestVo) {

        designBlueprintDelegator.query(queryRequestVo);
        return null;
    }

    Boolean update(DesignBlueprintUpdateDTO designBlueprintUpdateDTO) {
        designBlueprintDelegator.update(designBlueprintUpdateDTO);
        return null;
    }
    int delete(String id) {
        designBlueprintDelegator.delete(id);
        return 0;
    }
    DesignBlueprintQueryViewDTO detail(String id) {
        designBlueprintDelegator.get(id);
        return null;
    }

}
