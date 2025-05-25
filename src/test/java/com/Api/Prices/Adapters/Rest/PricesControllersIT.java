package com.Api.Prices.Adapters.Rest;

import com.Api.Prices.Application.Dto.PricesDTO;
import com.Api.Prices.Domain.Port.PricesServices;
import com.Api.Prices.Domain.Model.Prices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricesControllersIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricesServices pricesServices;

    @Test
    void testGetPriceSuccess() throws Exception {

        PricesDTO pricesDTO = new PricesDTO(
                1L,
                1L,
                35455L,
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                new BigDecimal("35.50"),
                "EUR"
        );

        given(pricesServices.getPrices(anyLong(), anyLong(), any(LocalDateTime.class)))
                .willReturn(pricesDTO);

        mockMvc.perform(get("/api/prices/find")
                        .param("product_id", "35455")
                        .param("brand_id", "1")
                        .param("application_date", "2020-06-14 10:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(new BigDecimal("35.5")));
    }

    @Test
    void testGetPriceNotFound() throws Exception {
        given(pricesServices.getPrices(anyLong(), anyLong(), any(LocalDateTime.class)))
                .willReturn(null);

        mockMvc.perform(get("/api/prices/find")
                        .param("product_id", "35455")
                        .param("brand_id", "1")
                        .param("application_date", "2020-06-14 10:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPriceBadRequest() throws Exception {
        mockMvc.perform(get("/api/prices/find")
                        .param("brandId", "1")
                        .param("date", "2020-06-14T10:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
