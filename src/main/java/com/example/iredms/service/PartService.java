package com.example.iredms.service;

import com.example.iredms.common.BaseResponse;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartViewDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkViewDTO;

import java.util.ArrayList;
import java.util.List;

public interface PartService {
    public PartViewDTO CreatePart(PartCreateDTO partCreate);
    public ArrayList<ClassificationNodeViewDTO> getClassificationList();
    public PartViewDTO updatePart(String id);
    public void deletePart(String id) throws IllegalAccessException;
}