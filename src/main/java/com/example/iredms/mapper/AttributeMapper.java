package com.example.iredms.mapper;

import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionLinkDelegator;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttributeMapper {
    @Autowired
    private EXADefinitionLinkDelegator exaDefinitionLinkDelegator;

    //根据分类ID获取其属性
    public List<EXADefinitionLinkViewDTO> getAttributeLinkByTargetID(String id) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("target.id", ConditionType.EQUAL,id);
        RDMPageVO rdmPageVO = new RDMPageVO(1,Integer.MAX_VALUE);
        return exaDefinitionLinkDelegator.find(queryRequestVo,rdmPageVO);
    }
}
