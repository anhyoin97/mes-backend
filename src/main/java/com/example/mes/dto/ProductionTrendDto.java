package com.example.mes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductionTrendDto {
    private String date;       // yyyy-MM-dd
    private Long quantity;     // 생산 수량 합계
}