package com.example.prices.mappers;

import com.company.prices.api.model.PriceResponse;
import com.example.prices.infrastructure.entities.PriceVO;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class PriceResponseMapper {

    public Optional<PriceResponse> mapToPriceResponse(List<PriceVO> priceVOs) {
        if (priceVOs == null || priceVOs.isEmpty()) {
            return Optional.empty();
        }

        return priceVOs.stream()
                .sorted(Comparator.comparing(PriceVO::getPriority).reversed())
                .findFirst()
                .map(this::toPriceResponse);
    }

    private PriceResponse toPriceResponse(PriceVO priceVO) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setProductId(priceVO.getProductId());
        priceResponse.setBrandId(priceVO.getBrandId());
        priceResponse.setPriceList(priceVO.getPriceList());
        priceResponse.setStartDate(priceVO.getStartDate());
        priceResponse.setEndDate(priceVO.getEndDate());
        priceResponse.setPrice(priceVO.getPrice());
        priceResponse.setCurrency(priceVO.getCurrency());
        priceResponse.setPriority(priceVO.getPriority());
        return priceResponse;
    }
}