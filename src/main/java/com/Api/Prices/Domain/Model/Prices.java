package com.Api.Prices.Domain.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prices")
@lombok.Generated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prices_id")
    private Long pricesId;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "price_list", nullable = false)
    private Long priceList;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "priority", nullable = false)
    private Long priority;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "curr", nullable = false)
    private String curr;
}
