package com.Api.Prices.Application.Mapper;

import com.Api.Prices.Application.Dto.PricesDTO;
import com.Api.Prices.Domain.Model.Prices;
import org.springframework.stereotype.Component;

@Component
public class PricesMapper {
    public PricesDTO toDto(Prices price) {
        PricesDTO dto = new PricesDTO();
        dto.setBrandId(price.getBrand_id());
        dto.setProductId(price.getProduct_id());
        dto.setPriceList(price.getPrice_list());
        dto.setStartDate(price.getStart_date());
        dto.setEndDate(price.getEnd_date());
        dto.setPrice(price.getPrice());
        dto.setCurrency(price.getCurr());
        return dto;
    }

    public Prices toEntity(PricesDTO dto) {
        Prices price = new Prices();
        price.setBrand_id(dto.getBrandId());
        price.setProduct_id(dto.getProductId());
        price.setPrice_list(dto.getPriceList());
        price.setStart_date(dto.getStartDate());
        price.setEnd_date(dto.getEndDate());
        price.setPrice(dto.getPrice());
        price.setCurr(dto.getCurrency());
        return price;
    }
}
