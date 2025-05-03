package com.example.mes.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mes.dto.DefectTrendDto;
import com.example.mes.dto.ProductionTrendDto;
import com.example.mes.dto.WorkOrderCountDto;
import com.example.mes.repository.EquipmentRepository;
import com.example.mes.repository.ProductionResultRepository;
import com.example.mes.repository.WorkOrderRepository;
import com.example.mes.service.ProductionResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
	private final EquipmentRepository equipmentRepository;
	private final WorkOrderRepository workOrderRepository;
	private final ProductionResultRepository productionResultRepository;
	private final ProductionResultService productionResultService; //
	
	// 설비 상태 비율
    @GetMapping("/equipment-status")
    public Map<String, Long> getEquipmentStatus() {
        Map<String, Long> result = new HashMap<>();
        result.put("사용", equipmentRepository.countByStatus("사용"));
        result.put("미사용", equipmentRepository.countByStatus("미사용"));
        return result;
    }
    
    // 제품별 작업지시 건수
    @GetMapping("/work-order-count")
    public List<WorkOrderCountDto> getWorkOrderCountByProduct() {
        return workOrderRepository.countWorkOrdersGroupedByProduct();
    }
    
    @GetMapping("/production-trend")
    public List<ProductionTrendDto> getProductionTrend() {
        List<Object[]> rawResult = productionResultRepository.findProductionTrendNative();

	    return rawResult.stream()
	    		.map(row -> new ProductionTrendDto(
	                    (String) row[0],                            // date
	                    ((BigDecimal) row[1]).longValue()))         // quantity
	           	.collect(Collectors.toList());
    }
    
    // 불량률 현황
    @GetMapping("/defect-trend")
    public List<DefectTrendDto> getDefectTrend() {
        return productionResultService.getDefectTrendLast7Days();
    }
}
