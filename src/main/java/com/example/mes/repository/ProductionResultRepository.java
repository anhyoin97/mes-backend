package com.example.mes.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.mes.entity.ProductionResult;
import java.util.List;

public interface ProductionResultRepository extends CrudRepository<ProductionResult, Long> {

    @Query(value = """
        SELECT DATE_FORMAT(start_time, '%Y-%m-%d') AS date, 
               SUM(produced_qty) AS quantity
        FROM production_result
        WHERE start_time >= CURDATE() - INTERVAL 7 DAY
        GROUP BY DATE_FORMAT(start_time, '%Y-%m-%d')
        ORDER BY date
        """, nativeQuery = true)
    List<Object[]> findProductionTrendNative();
    
    @Query(value = """
    	    SELECT
			    DATE_FORMAT(pr.start_time, '%Y-%m-%d') AS date,
			    SUM(pr.defective_qty) / SUM(pr.produced_qty) * 100 AS defectRate
			FROM
			    production_result pr
			WHERE
			    pr.start_time >= CURDATE() - INTERVAL 7 DAY
			GROUP BY
			    DATE_FORMAT(pr.start_time, '%Y-%m-%d')
			ORDER BY
			    date
    	""", nativeQuery = true)
	List<Object[]> findDefectTrendLast7DaysRaw();
}