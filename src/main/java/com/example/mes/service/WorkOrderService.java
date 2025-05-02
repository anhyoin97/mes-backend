package com.example.mes.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mes.entity.Product;
import com.example.mes.entity.WorkOrder;
import com.example.mes.repository.ProductRepository;
import com.example.mes.repository.WorkOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final ProductRepository productRepository;

    public List<WorkOrder> getAllOrders() {
        return workOrderRepository.findAll();
    }

    public WorkOrder createOrder(Long productId, int quantity, LocalDateTime dueDate, String status) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        WorkOrder order = new WorkOrder();
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setDueDate(dueDate);
        order.setStatus(status);

        return workOrderRepository.save(order);
    }
}
