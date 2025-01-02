package com.example.iredms.service;

import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;

import java.util.ArrayList;

public interface ClassificationService {
    public ArrayList<ClassificationNodeViewDTO> getClassificationList();
}
