package com.Api.Prices.Domain.Model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PricesTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        Prices prices = new Prices(
                1L,
                1L,
                start,
                end,
                1L,
                35455L,
                0L,
                new BigDecimal("35.50"),
                "EUR"
        );

        assertEquals(1L, prices.getPricesId());
        assertEquals(1L, prices.getBrandId());
        assertEquals(start, prices.getStartDate());
        assertEquals(end, prices.getEndDate());
        assertEquals(1L, prices.getPriceList());
        assertEquals(35455L, prices.getProductId());
        assertEquals(0L, prices.getPriority());
        assertEquals(new BigDecimal("35.50"), prices.getPrice());
        assertEquals("EUR", prices.getCurr());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Prices prices = new Prices();
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        prices.setPricesId(2L);
        prices.setBrandId(2L);
        prices.setStartDate(start);
        prices.setEndDate(end);
        prices.setPriceList(2L);
        prices.setProductId(12345L);
        prices.setPriority(1L);
        prices.setPrice(new BigDecimal("99.99"));
        prices.setCurr("USD");

        assertEquals(2L, prices.getPricesId());
        assertEquals(2L, prices.getBrandId());
        assertEquals(start, prices.getStartDate());
        assertEquals(end, prices.getEndDate());
        assertEquals(2L, prices.getPriceList());
        assertEquals(12345L, prices.getProductId());
        assertEquals(1L, prices.getPriority());
        assertEquals(new BigDecimal("99.99"), prices.getPrice());
        assertEquals("USD", prices.getCurr());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        Prices prices1 = new Prices(1L, 1L, start, end, 1L, 35455L, 0L, new BigDecimal("35.50"), "EUR");
        Prices prices2 = new Prices(1L, 1L, start, end, 1L, 35455L, 0L, new BigDecimal("35.50"), "EUR");
        Prices prices3 = new Prices();

        assertEquals(prices1, prices2);
        assertEquals(prices1.hashCode(), prices2.hashCode());
        assertNotEquals(prices1, prices3);
    }

    @Test
    void testToString() {
        Prices prices = new Prices();
        String str = prices.toString();
        assertNotNull(str);
        assertTrue(str.contains("Prices"));
    }
}