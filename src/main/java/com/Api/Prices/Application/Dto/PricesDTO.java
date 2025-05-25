package com.Api.Prices.Application.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@lombok.Generated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricesDTO {

    private Long pricesId;
    private Long brandId;
    private Long productId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private String currency;

}
