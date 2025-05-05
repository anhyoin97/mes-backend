package com.example.mes.dto;

import lombok.Data;

@Data
public class WorkOrderRequest {
	private Long productId;
    private Integer quantity;
    private String status;
    private String dueDate; // LocalDateTime 대신 문자열로 받고 parse
}
