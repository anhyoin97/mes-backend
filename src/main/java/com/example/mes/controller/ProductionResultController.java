package com.example.mes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mes.dto.ProductionResultRequest;
import com.example.mes.entity.ProductionResult;
import com.example.mes.service.ProductionResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ProductionResultController {

    private final ProductionResultService resultService;

    @GetMapping
    public ResponseEntity<List<ProductionResult>> getAll() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @PostMapping
    public ResponseEntity<ProductionResult> create(@RequestBody ProductionResultRequest req) {
        ProductionResult result = resultService.recordResult(
            req.getEquipmentId(),
            req.getWorkOrderId(),
            req.getProducedQty(),
            req.getDefectiveQty(),
            req.getStartTime(),
            req.getEndTime()
        );
        return ResponseEntity.ok(result);
    }
}