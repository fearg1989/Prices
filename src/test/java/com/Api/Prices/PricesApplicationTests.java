package com.Api.Prices;

import org.junit.jupiter.api.Test;
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

	@Test
	void testGetPrice_Test2() throws Exception {
		mockMvc.perform(get("/api/prices/find")
						.param("application_date", "2020-06-14 16:00:00")
						.param("product_id", "35455")
						.param("brand_id", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price_list").value(2))
				.andExpect(jsonPath("$.price").value(25.45))
				.andExpect(jsonPath("$.product_id").value(35455))
				.andExpect(jsonPath("$.application_date").value("2020-06-14 16:00:00"))
				.andExpect(jsonPath("$.brand_id").value(1));
	}

	@Test
	void testGetPrice_Test3() throws Exception {
		mockMvc.perform(get("/api/prices/find")
						.param("application_date", "2020-06-14 21:00:00")
						.param("product_id", "35455")
						.param("brand_id", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price_list").value(1))
				.andExpect(jsonPath("$.price").value(35.50))
				.andExpect(jsonPath("$.product_id").value(35455))
				.andExpect(jsonPath("$.application_date").value("2020-06-14 21:00:00"))
				.andExpect(jsonPath("$.brand_id").value(1));
	}

	@Test
	void testGetPrice_Test4() throws Exception {
		mockMvc.perform(get("/api/prices/find")
						.param("application_date", "2020-06-15 10:00:00")
						.param("product_id", "35455")
						.param("brand_id", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price_list").value(3))
				.andExpect(jsonPath("$.price").value(30.50))
				.andExpect(jsonPath("$.product_id").value(35455))
				.andExpect(jsonPath("$.application_date").value("2020-06-15 10:00:00"))
				.andExpect(jsonPath("$.brand_id").value(1));
	}

	@Test
	void testGetPrice_Test5() throws Exception {
		mockMvc.perform(get("/api/prices/find")
						.param("application_date", "2020-06-16 21:00:00")
						.param("product_id", "35455")
						.param("brand_id", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price_list").value(4))
				.andExpect(jsonPath("$.price").value(38.95))
				.andExpect(jsonPath("$.product_id").value(35455))
				.andExpect(jsonPath("$.application_date").value("2020-06-16 21:00:00"))
				.andExpect(jsonPath("$.brand_id").value(1));
	}

}
