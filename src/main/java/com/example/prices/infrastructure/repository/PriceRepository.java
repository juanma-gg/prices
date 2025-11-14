package com.example.prices.infrastructure.repository;

import com.example.prices.infrastructure.entities.PriceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceVO, Long> {
    @Query(value = """
        SELECT * FROM prices p
        WHERE p.product_id = :productId
          AND p.brand_id = :brandId
          AND :applicationDate BETWEEN p.start_date AND p.end_date
        ORDER BY p.priority DESC
        LIMIT 1
        """, nativeQuery = true)
    List<PriceVO> findApplicablePrice(
            @Param("applicationDate") LocalDateTime applicationDate,
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId
    );
}

