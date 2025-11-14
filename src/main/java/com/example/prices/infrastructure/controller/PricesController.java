package com.example.prices.infrastructure.controller;

import com.company.prices.api.PricesApi;
import com.company.prices.api.model.PriceResponse;
import com.example.prices.application.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PricesController implements PricesApi {

    private final PriceService priceService;

    @Autowired
    public PricesController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public ResponseEntity<PriceResponse> getPrices(
            LocalDateTime applicationDate,
            Integer productId,
            Integer brandId) {
        Optional<PriceResponse> priceResponse = priceService.getPriceFiltered(applicationDate, productId, brandId);

        if (priceResponse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return priceResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
