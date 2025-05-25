package com.Api.Prices.Application.Service;

import com.Api.Prices.Application.Dto.PricesDTO;
import com.Api.Prices.Domain.Exception.PriceNotFoundException;
import com.Api.Prices.Domain.Model.Prices;
import com.Api.Prices.Application.Mapper.PricesMapper;
import com.Api.Prices.Domain.Port.PricesRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PricesServicesImpl implements com.Api.Prices.Domain.Port.PricesServices {

    private final PricesRepository pricesRepository;
    private final PricesMapper pricesMapper;

    public PricesServicesImpl(PricesRepository pricesRepository, PricesMapper pricesMapper) {
        this.pricesRepository = pricesRepository;
        this.pricesMapper = pricesMapper;
    }

    @Override
    @Cacheable(value = "pricesCache", key = "#productId + '-' + #brandId + '-' + #applicationDate")
    public PricesDTO getPrices(Long productId, Long brandId, LocalDateTime applicationDate) {
        Prices price = pricesRepository.findPrice(productId, brandId, applicationDate)
                .stream().findFirst()
                .orElseThrow(() -> new PriceNotFoundException(
                        "No price found for product: " + productId +
                                " and brand " + brandId +
                                " at " + applicationDate));

        return pricesMapper.toDto(price);
    }
}
