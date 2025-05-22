package com.Api.Prices.Infrastructure.Repository;

import com.Api.Prices.Domain.Model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPriceRepository extends JpaRepository<Prices, Long> {

    @Query("SELECT p FROM Prices p WHERE p.product_id = :product_id AND p.brand_id = :brand_id AND :application_date BETWEEN p.start_date AND p.end_date ORDER BY p.priority DESC")
    List<Prices> findPrice(Long product_id, Long brand_id, LocalDateTime application_date);

}
