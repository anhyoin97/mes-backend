package com.example.mes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mes.entity.ProductionResult;

@Repository
public interface ProductionResultRepository extends JpaRepository<ProductionResult, Long> {
}
