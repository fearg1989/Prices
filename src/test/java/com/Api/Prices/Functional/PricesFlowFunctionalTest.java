package com.Api.Prices.Functional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricesFlowFunctionalTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFullPriceFlowSuccess() throws Exception {
        mockMvc.perform(get("/api/prices/find")
                        .param("product_id", "35455")
                        .param("brand_id", "1")
                        .param("application_date", "2020-06-14 10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price_list").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.product_id").value(35455))
                .andExpect(jsonPath("$.application_date").value("2020-06-14 10:00:00"))
                .andExpect(jsonPath("$.brand_id").value(1));
    }

    @Test
    void testFullPriceFlowNotFound() throws Exception {
        mockMvc.perform(get("/api/prices/find")
                        .param("product_id", "99999")
                        .param("brand_id", "1")
                        .param("application_date", "2025-01-01 00:00:00"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void testFullPriceFlowBadRequest() throws Exception {
        mockMvc.perform(get("/api/prices/find")
                        .param("brand_id", "1")
                        .param("application_date", "2020-06-14 10:00:00"))
                .andExpect(status().isBadRequest());
    }
}
