package com.Api.Prices;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PricesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetPrice_Test1() throws Exception {
		mockMvc.perform(get("/api/prices/find")
						.param("application_date", "2020-06-14 10:00:00")
						.param("product_id", "35455")
						.param("brand_id", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price_list").value(1))
				.andExpect(jsonPath("$.price").value(35.50))
				.andExpect(jsonPath("$.product_id").value(35455))
				.andExpect(jsonPath("$.application_date").value("2020-06-14 10:00:00"))
				.andExpect(jsonPath("$.brand_id").value(1));
	}

	@ParameterizedTest
	@CsvSource({
			"2020-06-14 10:00:00, 35455, 1, 35.50",
			"2020-06-14 16:00:00, 35455, 1, 25.45",
			"2020-06-14 21:00:00, 35455, 1, 35.50",
			"2020-06-15 10:00:00, 35455, 1, 30.50",
			"2020-06-16 21:00:00, 35455, 1, 38.95"
	})
	public void testFindPrice(String applicationDate, int productId, int brandId, double expectedPrice) throws Exception {
		mockMvc.perform(get("/api/prices/find")
						.param("product_id", String.valueOf(productId))
						.param("brand_id", String.valueOf(brandId))
						.param("application_date", applicationDate))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(expectedPrice));
	}

	@Test
	void testGetPrice_NotFound() throws Exception {
		mockMvc.perform(get("/api/prices/find")
						.param("application_date", "2021-01-01 00:00:00")
						.param("product_id", "35456")
						.param("brand_id", "1"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.error").value("Not Found"))
				.andExpect(jsonPath("$.message", Matchers.containsString("No price found for product")))
				.andExpect(jsonPath("$.path").value("/api/prices/find"));
	}

}
