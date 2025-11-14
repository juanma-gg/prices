package com.example.prices.mappers;

import com.example.prices.infrastructure.entities.PriceVO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceVOMapper {
    public PriceVO mapToPriceVO(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return PriceVO.builder()
                .startDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();
    }
}
