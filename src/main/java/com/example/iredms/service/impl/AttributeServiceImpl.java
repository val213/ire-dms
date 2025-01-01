package com.example.iredms.service.impl;

import com.example.iredms.mapper.AttributeMapper;
import com.example.iredms.service.AttributeService;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    AttributeMapper attributeMapper;

    //根据分类id获取其属性
    @Override
    public ArrayList<EXADefinitionViewDTO> getClassificationAttribute(String classificationID) {
        List<EXADefinitionLinkViewDTO> v = attributeMapper.getAttributeLinkByTargetID(classificationID);
        ArrayList<EXADefinitionViewDTO> ret = new ArrayList<>();
        if (v == null) {
            return ret;
        }
        for (EXADefinitionLinkViewDTO d : v) {
            ret.add(d.getSource());
        }
        return ret;
    }
}
