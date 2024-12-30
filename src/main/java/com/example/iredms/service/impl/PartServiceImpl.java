package com.example.iredms.service.impl;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.service.PartService;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.*;
import com.huawei.innovation.rdm.xdm.delegator.ClassificationNodeDelegator;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionDelegator;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionLinkDelegator;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    PartDelegator partDelegator;

    //创建部件
    @Override
    public PartViewDTO CreatePart(PartCreateDTO partCreate) {
        PartMasterCreateDTO pmcd = new PartMasterCreateDTO();
        partCreate.setMaster(pmcd);
        PartBranchCreateDTO pbcd = new PartBranchCreateDTO();
        partCreate.setBranch(pbcd);
        PartViewDTO createPart = partDelegator.create(partCreate);
        return createPart;
    }



    //更新部件
    @Override
    public PartViewDTO updatePart(String id) {
        PartUpdateDTO partUpdateDTO = new PartUpdateDTO();
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("id", ConditionType.EQUAL, id);

        return null;
    }

    //删除部件
    @Override
    public void deletePart(String id) throws IllegalAccessException {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalAccessException("ID不能为空.");
        }

        DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("id", ConditionType.EQUAL,"");
        deleteByConditionVo.setCondition(queryRequestVo);

        try {
            int result = partDelegator.deleteByCondition(deleteByConditionVo);
            if (result <= 0) {
                throw new RuntimeException("删除part失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while delete part with id: " + id,e);
        }


    }
}
