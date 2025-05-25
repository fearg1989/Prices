package com.Api.Prices.Domain.Port;

import java.time.LocalDateTime;

import com.Api.Prices.Application.Dto.PricesDTO;

public interface PricesServices {

    PricesDTO getPrices(Long productId, Long brandId, LocalDateTime applicationDate);

}
