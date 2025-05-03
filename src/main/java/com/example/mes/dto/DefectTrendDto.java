package com.example.mes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DefectTrendDto {
	private String date;
    private Double defectRate; // 소수점 표시 가능해야 함

}