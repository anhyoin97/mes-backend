package com.example.mes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mes.dto.WorkOrderCountDto;
import com.example.mes.entity.WorkOrder;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
	@Query("SELECT new com.example.mes.dto.WorkOrderCountDto(p.name, COUNT(w)) " +
		       "FROM WorkOrder w JOIN w.product p GROUP BY p.name")
	List<WorkOrderCountDto> countWorkOrdersGroupedByProduct();
}
