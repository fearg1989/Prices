package com.Api.Prices.Domain.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "prices")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prices_id;
    @Column(name = "brandId", nullable = false)
    private Long brandId;
    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "priceList", nullable = false)
    private Long priceList;
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name = "priority", nullable = false)
    private Long priority;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "curr", nullable = false)
    private String curr;

    public Prices() {
    }

    public Prices(Long brandId, LocalDateTime startDate, LocalDateTime endDate, Long priceList, Long productId, Long priority,
                  BigDecimal price, String curr) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

}
