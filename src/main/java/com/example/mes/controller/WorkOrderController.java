package com.example.mes.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mes.entity.WorkOrder;
import com.example.mes.service.WorkOrderService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/work-orders")
@RequiredArgsConstructor
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    @GetMapping
    public ResponseEntity<List<WorkOrder>> getAllOrders() {
        return ResponseEntity.ok(workOrderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<WorkOrder> createOrder(@RequestBody Map<String, Object> body) {
        Long productId = Long.valueOf(body.get("productId").toString());
        int quantity = (int) body.get("quantity");
        String status = body.get("status").toString();
        LocalDateTime dueDate = LocalDateTime.parse(body.get("dueDate").toString());

        WorkOrder created = workOrderService.createOrder(productId, quantity, dueDate, status);
        return ResponseEntity.ok(created);
    }
}
