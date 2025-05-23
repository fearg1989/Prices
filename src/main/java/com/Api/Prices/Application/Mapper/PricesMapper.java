package com.Api.Prices.Application.Mapper;

import com.Api.Prices.Application.Dto.PricesDTO;
import com.Api.Prices.Domain.Model.Prices;
import org.springframework.stereotype.Component;

@Component
public class PricesMapper {
    public PricesDTO toDto(Prices price) {
        PricesDTO dto = new PricesDTO();
        dto.setBrandId(price.getBrandId());
        dto.setProductId(price.getProductId());
        dto.setPriceList(price.getPriceList());
        dto.setStartDate(price.getStartDate());
        dto.setEndDate(price.getEndDate());
        dto.setPrice(price.getPrice());
        dto.setCurrency(price.getCurr());
        return dto;
    }

    public Prices toEntity(PricesDTO dto) {
        Prices price = new Prices();
        price.setBrandId(dto.getBrandId());
        price.setProductId(dto.getProductId());
        price.setPriceList(dto.getPriceList());
        price.setStartDate(dto.getStartDate());
        price.setEndDate(dto.getEndDate());
        price.setPrice(dto.getPrice());
        price.setCurr(dto.getCurrency());
        return price;
    }
}
