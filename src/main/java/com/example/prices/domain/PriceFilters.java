package com.example.prices.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class PriceFilters {
    private LocalDateTime startDate;
    private Integer productId;
    private Integer brandId;
}
