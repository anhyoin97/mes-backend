package com.example.mes.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.mes.dto.DefectTrendDto;
import com.example.mes.dto.ProductionResultRequest;
import com.example.mes.dto.ProductionResultResponse;
import com.example.mes.entity.Equipment;
import com.example.mes.entity.ProductionResult;
import com.example.mes.entity.WorkOrder;
import com.example.mes.repository.EquipmentRepository;
import com.example.mes.repository.ProductionResultRepository;
import com.example.mes.repository.WorkOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductionResultService {

    private final ProductionResultRepository resultRepository;
    private final EquipmentRepository equipmentRepository;
    private final WorkOrderRepository workOrderRepository;

    public List<ProductionResultResponse> getAllResults() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        return resultRepository.findAll().stream()
                .map(result -> new ProductionResultResponse(
                        result.getId(),
                        result.getWorkOrder().getProduct().getName(),
                        result.getEquipment().getName(),
                        result.getProducedQty(),
                        result.getDefectiveQty(),
                        result.getStartTime().format(formatter),
                        result.getEndTime().format(formatter)
                ))
                .collect(Collectors.toList());
    }

    public ProductionResult createResult(ProductionResultRequest dto) {
        WorkOrder workOrder = workOrderRepository.findById(dto.getWorkOrderId())
            .orElseThrow(() -> new IllegalArgumentException("작업지시 없음"));

        Equipment equipment = equipmentRepository.findById(dto.getEquipmentId())
            .orElseThrow(() -> new IllegalArgumentException("설비 없음"));

        ProductionResult result = new ProductionResult();
        result.setWorkOrder(workOrder);
        result.setEquipment(equipment);
        result.setProducedQty(dto.getQuantity());
        result.setDefectiveQty(dto.getDefectCount());
        result.setStartTime(dto.getStartTime());
        result.setEndTime(dto.getEndTime());

        return resultRepository.save(result); 
    }
    
    public List<DefectTrendDto> getDefectTrendLast7Days() {
        List<Object[]> rawResults = resultRepository.findDefectTrendLast7DaysRaw();

        return rawResults.stream()
                .map(row -> new DefectTrendDto((String) row[0], ((Number) row[1]).doubleValue()))
                .toList();
    }
}
