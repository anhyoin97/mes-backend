package com.example.mes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductionResultResponse {
    private Long id;
    private String productName;
    private String equipmentName;
    private int producedQty;
    private int defectiveQty;
    private String startTime;
    private String endTime;
}