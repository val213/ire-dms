package com.example.iredms.service.impl;

import com.example.iredms.service.ClassificationService;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.xdm.delegator.ClassificationNodeDelegator;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationNodeDelegator classificationNodeDelegator;

    //获取分类列表
    @Override
    public ArrayList<ClassificationNodeViewDTO> getClassificationList() {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("businessCode", ConditionType.LIKE,"");
        RDMPageVO rdmPageVO = new RDMPageVO(1,Integer.MAX_VALUE);
        return (ArrayList<ClassificationNodeViewDTO>) classificationNodeDelegator.find(queryRequestVo, rdmPageVO);
    }
}
