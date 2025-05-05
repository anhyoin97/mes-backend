package com.example.mes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mes.dto.ProductionResultRequest;
import com.example.mes.dto.ProductionResultResponse;
import com.example.mes.entity.ProductionResult;
import com.example.mes.service.ProductionResultService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ProductionResultController {

    private final ProductionResultService resultService;

    @GetMapping
    public ResponseEntity<List<ProductionResultResponse>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @PostMapping
    public ResponseEntity<ProductionResult> createResult(@RequestBody ProductionResultRequest dto) {
        ProductionResult saved = resultService.createResult(dto); 
        return ResponseEntity.ok(saved);
    }
}