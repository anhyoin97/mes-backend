package com.example.mes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkOrderCountDto {
    private String productName;
    private long orderCount;
}
