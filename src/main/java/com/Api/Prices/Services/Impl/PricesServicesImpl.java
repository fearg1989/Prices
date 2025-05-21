package com.Api.Prices.Services.Impl;

import com.Api.Prices.Models.Prices;
import com.Api.Prices.Repository.PricesRepository;
import com.Api.Prices.Services.PricesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PricesServicesImpl implements PricesServices {

    @Autowired
    private PricesRepository pricesRepository;

   @Override
    public Prices getPrices(Long product_id, Long brand_id, LocalDateTime application_date) {
        return pricesRepository.findPrice(product_id, brand_id, application_date);
    }
}
