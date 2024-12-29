package com.example.iredms.dto;

import lombok.Data;

@Data
public class ProductDeleteRequestDTO {
    private String modifier;
    private int id;
    private String applicationId;
}