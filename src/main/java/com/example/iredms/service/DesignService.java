package com.example.iredms.service;

import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintViewDTO;

import java.util.List;

public interface DesignService {
    public DesignBlueprintViewDTO createDesignBlueprint(DesignBlueprintViewDTO designBlueprintViewDTO);
    public DesignBlueprintViewDTO updateDesignBlueprint(DesignBlueprintViewDTO designBlueprintViewDTO);
    public int deleteDesignBlueprint(DesignBlueprintViewDTO designBlueprintViewDTO);

}