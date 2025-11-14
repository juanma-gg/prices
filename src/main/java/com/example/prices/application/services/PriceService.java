package com.example.prices.application.services;

import com.company.prices.api.model.PriceResponse;
import com.example.prices.infrastructure.repository.PriceRepository;
import com.example.prices.mappers.PriceResponseMapper;
import com.example.prices.mappers.PriceVOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final PriceResponseMapper priceResponseMapper;

    @Autowired
    public PriceService(PriceRepository priceRepository, PriceVOMapper priceVOMapper, PriceResponseMapper priceResponseMapper) {
        this.priceRepository = priceRepository;
        this.priceResponseMapper = priceResponseMapper;
    }

    public Optional<PriceResponse> getPriceFiltered(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return this.priceResponseMapper.mapToPriceResponse(
                this.priceRepository.findApplicablePrice(applicationDate, productId, brandId)
        );
    }
}
