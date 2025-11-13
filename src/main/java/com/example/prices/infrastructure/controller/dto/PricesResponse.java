package com.example.prices.infrastructure.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PricesResponse {
    List<PriceDto> priceDtoList;
}
