package com.Api.Prices.Domain.Port;

import com.Api.Prices.Domain.Model.Prices;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository{
    List<Prices> findPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
