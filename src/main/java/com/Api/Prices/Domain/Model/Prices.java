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
    @Column(name = "brand_id", nullable = false)
    private Long brand_id;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime start_date;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime end_date;
    @Column(name = "price_list", nullable = false)
    private Long price_list;
    @Column(name = "product_id", nullable = false)
    private Long product_id;
    @Column(name = "priority", nullable = false)
    private Long priority;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "curr", nullable = false)
    private String curr;

    public Prices() {
    }

    public Prices(Long brand_id, LocalDateTime start_date, LocalDateTime end_date, Long price_list, Long product_id, Long priority,
                  BigDecimal price, String curr) {
        this.brand_id = brand_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price_list = price_list;
        this.product_id = product_id;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public Long getBrandId() {
        return brand_id;
    }

    public void setBrandId(Long brand_id) {
        this.brand_id = brand_id;
    }

    public LocalDateTime getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEndDate() {
        return end_date;
    }

    public void setEndDate(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public Long getPriceList() {
        return price_list;
    }

    public void setPriceList(Long price_list) {
        this.price_list = price_list;
    }

    public Long getProductId() {
        return product_id;
    }

    public void setProductId(Long product_id) {
        this.product_id = product_id;
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
