package com.Api.Prices.Services;

import java.time.LocalDateTime;
import java.util.List;

import com.Api.Prices.Models.Prices;

public interface PricesServices {

    Prices getPrices(Long product_id, Long brand_id, LocalDateTime application_date);

}
