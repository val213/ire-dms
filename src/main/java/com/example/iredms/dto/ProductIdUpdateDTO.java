package com.example.iredms.dto;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.EngineeringStage;
import lombok.Data;

@Data
public class ProductIdUpdateDTO {
    private String productName;
    private String productOwner;
    private EngineeringStage productStage;
    private String productInformation;
}
