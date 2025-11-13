package com.example.prices.infrastructure.controller;

import com.company.prices.api.PricesApi;
import com.company.prices.api.model.PriceResponse;
import com.example.prices.application.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class PricesController implements PricesApi {

    private PriceService priceService;

    @Autowired
    public PricesController(PriceService priceService) {
        this.priceService = priceService;
    }
    @Override
    public ResponseEntity<List<PriceResponse>> getPrices(
            OffsetDateTime applicationDate,
            Integer productId,
            Integer brandId) {

        PricesResponse pricesResponse =this.getPricesService(applicationDate, productId, brandId);
        if (CollectionUtils.isEmpty(pricesResponse.getPricesDtos())) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pricesResponse);
    }
}
