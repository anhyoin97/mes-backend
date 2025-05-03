package com.example.mes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.mes.dto.DefectTrendDto;
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

    public List<ProductionResult> getAllResults() {
	    return StreamSupport.stream(resultRepository.findAll().spliterator(), false)
	                        .collect(Collectors.toList());
    }

    public ProductionResult recordResult(Long equipmentId, Long workOrderId, int producedQty, int defectiveQty,
                                         LocalDateTime startTime, LocalDateTime endTime) {

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

        WorkOrder workOrder = workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new IllegalArgumentException("WorkOrder not found"));

        ProductionResult result = new ProductionResult();
        result.setEquipment(equipment);
        result.setWorkOrder(workOrder);
        result.setProducedQty(producedQty);
        result.setDefectiveQty(defectiveQty);
        result.setStartTime(startTime);
        result.setEndTime(endTime);

        return resultRepository.save(result);
    }
    
    public List<DefectTrendDto> getDefectTrendLast7Days() {
        List<Object[]> rawResults = resultRepository.findDefectTrendLast7DaysRaw();

        return rawResults.stream()
                .map(row -> new DefectTrendDto((String) row[0], ((Number) row[1]).doubleValue()))
                .toList();
    }
}
