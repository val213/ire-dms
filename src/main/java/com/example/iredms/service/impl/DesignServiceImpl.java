package com.example.iredms.service.impl;

import com.example.iredms.service.DesignService;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.DesignBlueprintDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesignServiceImpl implements DesignService {
    @Autowired
    private DesignBlueprintDelegator designBlueprintDelegator;

    public DesignServiceImpl(DesignBlueprintDelegator designBlueprintDelegator) {
        this.designBlueprintDelegator = designBlueprintDelegator;
    }

    @Override
    public DesignBlueprintViewDTO createDesignBlueprint(DesignBlueprintViewDTO designBlueprintViewDTO) {
        DesignBlueprintCreateDTO designBlueprint = new DesignBlueprintCreateDTO();
        if (designBlueprint.getBuleprintDescription() != null && !designBlueprint.getBuleprintDescription().isEmpty()) {
            designBlueprint.setBuleprintDescription(designBlueprint.getBuleprintDescription());
        }
        return designBlueprintDelegator.create(designBlueprint);
    }

    @Override
    public DesignBlueprintViewDTO updateDesignBlueprint(DesignBlueprintViewDTO designBlueprintViewDTO) {
        return null;
    }

    @Override
    public int deleteDesignBlueprint(DesignBlueprintViewDTO designBlueprintViewDTO) {
        if (designBlueprintViewDTO.getId() != null) {
            QueryRequestVo queryRequestVo = new QueryRequestVo();
            queryRequestVo.addCondition("id", ConditionType.EQUAL,designBlueprintViewDTO.getId());
            DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
            deleteByConditionVo.setCondition(queryRequestVo);
            return designBlueprintDelegator.deleteByCondition(deleteByConditionVo);
        } else return 0;
    }

}
