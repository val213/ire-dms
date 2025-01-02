package com.example.iredms.service;

import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;

import java.util.ArrayList;

public interface AttributeService {
    public ArrayList<EXADefinitionViewDTO> getClassificationAttribute(String classificationID);
}
