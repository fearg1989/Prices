package com.Api.Prices.Domain.Port;

import com.Api.Prices.Domain.Model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository{
    List<Prices> findPrice(Long product_id, Long brand_id, LocalDateTime application_date);
}
