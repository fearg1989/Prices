package com.Api.Prices.Infrastructure.Repository;

import com.Api.Prices.Domain.Model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPriceRepository extends JpaRepository<Prices, Long> {

    @Query("SELECT p FROM Prices p WHERE p.productId = :productId AND p.brandId = :brandId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<Prices> findPrice(Long productId, Long brandId, LocalDateTime applicationDate);

}
