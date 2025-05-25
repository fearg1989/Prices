package com.Api.Prices;

import com.Api.Prices.Adapters.Exception.GlobalExceptionHandler;
import com.Api.Prices.Application.Dto.PricesDTO;
import com.Api.Prices.Application.Mapper.PricesMapper;
import com.Api.Prices.Domain.Exception.PriceNotFoundException;
import com.Api.Prices.Domain.Model.Prices;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PricesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetPriceTest1() throws Exception {
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
	void testGetPriceNotFound() throws Exception {
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

	@Test
	void testGettersAndSetters() {
		Prices prices = new Prices();
		Long brandId = 1L;
		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate = startDate.plusDays(1);
		Long priceList = 2L;
		Long productId = 3L;
		Long priority = 4L;
		BigDecimal priceValue = new BigDecimal("99.99");
		String curr = "EUR";

		prices.setBrandId(brandId);
		prices.setStartDate(startDate);
		prices.setEndDate(endDate);
		prices.setPriceList(priceList);
		prices.setProductId(productId);
		prices.setPriority(priority);
		prices.setPrice(priceValue);
		prices.setCurr(curr);

		assertEquals(brandId, prices.getBrandId());
		assertEquals(startDate, prices.getStartDate());
		assertEquals(endDate, prices.getEndDate());
		assertEquals(priceList, prices.getPriceList());
		assertEquals(productId, prices.getProductId());
		assertEquals(priority, prices.getPriority());
		assertEquals(priceValue, prices.getPrice());
		assertEquals(curr, prices.getCurr());
	}

	@Test
	void testAllArgsConstructor() {
		Long brandId = 1L;
		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate = startDate.plusDays(1);
		Long priceList = 2L;
		Long productId = 3L;
		Long priority = 4L;
		BigDecimal priceValue = new BigDecimal("99.99");
		String curr = "USD";

		Prices prices = new Prices(null, brandId, startDate, endDate, priceList, productId, priority, priceValue, curr);

		assertEquals(brandId, prices.getBrandId());
		assertEquals(startDate, prices.getStartDate());
		assertEquals(endDate, prices.getEndDate());
		assertEquals(priceList, prices.getPriceList());
		assertEquals(productId, prices.getProductId());
		assertEquals(priority, prices.getPriority());
		assertEquals(priceValue, prices.getPrice());
		assertEquals(curr, prices.getCurr());
	}

	@Test
	void testNoArgsConstructorAndSettersAndGetters() {
		Prices prices = new Prices();
		prices.setPricesId(1L);
		prices.setBrandId(2L);
		prices.setStartDate(LocalDateTime.of(2020, 1, 1, 10, 0));
		prices.setEndDate(LocalDateTime.of(2020, 1, 2, 10, 0));
		prices.setPriceList(3L);
		prices.setProductId(4L);
		prices.setPriority(5L);
		prices.setPrice(new BigDecimal("99.99"));
		prices.setCurr("EUR");

		assertEquals(1L, prices.getPricesId());
		assertEquals(2L, prices.getBrandId());
		assertEquals(LocalDateTime.of(2020, 1, 1, 10, 0), prices.getStartDate());
		assertEquals(LocalDateTime.of(2020, 1, 2, 10, 0), prices.getEndDate());
		assertEquals(3L, prices.getPriceList());
		assertEquals(4L, prices.getProductId());
		assertEquals(5L, prices.getPriority());
		assertEquals(new BigDecimal("99.99"), prices.getPrice());
		assertEquals("EUR", prices.getCurr());
	}

	@Test
	void testLombokGeneratedMethods() {
		LocalDateTime now = LocalDateTime.now();
		Prices p1 = new Prices(1L, 1L, now, now, 1L, 1L, 1L, BigDecimal.ONE, "EUR");
		Prices p2 = new Prices();
		p2.setPricesId(1L);
		p2.setBrandId(1L);
		p2.setStartDate(now);
		p2.setEndDate(now);
		p2.setPriceList(1L);
		p2.setProductId(1L);
		p2.setPriority(1L);
		p2.setPrice(BigDecimal.ONE);
		p2.setCurr("EUR");

		assertEquals(p1, p2);
		assertEquals(p1.hashCode(), p2.hashCode());
		assertNotNull(p1.toString());
		assertEquals(1L, p1.getPricesId());
		assertEquals("EUR", p1.getCurr());
	}

	private final PricesMapper mapper = new PricesMapper();

	@Test
	void testToDto() {
		Prices prices = new Prices(
				null, // pricesId
				1L, // brandId
				LocalDateTime.of(2023, 1, 1, 10, 0),
				LocalDateTime.of(2023, 1, 2, 10, 0),
				2L, // priceList
				3L, // productId
				4L, // priority
				new BigDecimal("99.99"),
				"EUR"
		);

		PricesDTO dto = mapper.toDto(prices);

		assertEquals(prices.getBrandId(), dto.getBrandId());
		assertEquals(prices.getStartDate(), dto.getStartDate());
		assertEquals(prices.getEndDate(), dto.getEndDate());
		assertEquals(prices.getPriceList(), dto.getPriceList());
		assertEquals(prices.getProductId(), dto.getProductId());
		assertEquals(prices.getPrice(), dto.getPrice());
		assertEquals(prices.getCurr(), dto.getCurrency());
	}

	@Test
	void testToEntity() {
		PricesDTO dto = new PricesDTO();
		dto.setBrandId(1L);
		dto.setStartDate(LocalDateTime.of(2023, 1, 1, 10, 0));
		dto.setEndDate(LocalDateTime.of(2023, 1, 2, 10, 0));
		dto.setPriceList(2L);
		dto.setProductId(3L);
		dto.setPrice(new BigDecimal("99.99"));
		dto.setCurrency("USD");

		Prices prices = mapper.toEntity(dto);

		assertEquals(dto.getBrandId(), prices.getBrandId());
		assertEquals(dto.getStartDate(), prices.getStartDate());
		assertEquals(dto.getEndDate(), prices.getEndDate());
		assertEquals(dto.getPriceList(), prices.getPriceList());
		assertEquals(dto.getProductId(), prices.getProductId());
		assertEquals(dto.getPrice(), prices.getPrice());
		assertEquals(dto.getCurrency(), prices.getCurr());
	}

	@Test
	void testHandlePriceNotFoundException() {
		GlobalExceptionHandler handler = new GlobalExceptionHandler();
		PriceNotFoundException ex = new PriceNotFoundException("No price found");
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("uri=/api/prices/find");

		ResponseEntity<Object> response = handler.handlePriceNotFoundException(ex, request);

		assertEquals(404, ((Map<?, ?>) response.getBody()).get("status"));
		assertEquals("Not Found", ((Map<?, ?>) response.getBody()).get("error"));
		assertEquals("No price found", ((Map<?, ?>) response.getBody()).get("message"));
		assertEquals("/api/prices/find", ((Map<?, ?>) response.getBody()).get("path"));
	}

	@Test
	void testHandleIllegalArgumentException() {
		GlobalExceptionHandler handler = new GlobalExceptionHandler();
		IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("uri=/api/prices/find");

		ResponseEntity<Object> response = handler.handleIllegalArgumentException(ex, request);

		assertEquals(400, ((Map<?, ?>) response.getBody()).get("status"));
		assertEquals("Bad Request", ((Map<?, ?>) response.getBody()).get("error"));
		assertEquals("Invalid argument", ((Map<?, ?>) response.getBody()).get("message"));
		assertEquals("/api/prices/find", ((Map<?, ?>) response.getBody()).get("path"));
	}

	@Test
	void testHandleMissingServletRequestParameter() {
		GlobalExceptionHandler handler = new GlobalExceptionHandler();
		MissingServletRequestParameterException ex =
				new MissingServletRequestParameterException("product_id", "String");
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("uri=/api/prices/find");

		ResponseEntity<Object> response = handler.handleMissingServletRequestParameter(ex, request);

		assertEquals(400, ((Map<?, ?>) response.getBody()).get("status"));
		assertEquals("Bad Request", ((Map<?, ?>) response.getBody()).get("error"));
		assertEquals("Missing parameter: product_id", ((Map<?, ?>) response.getBody()).get("message"));
		assertEquals("/api/prices/find", ((Map<?, ?>) response.getBody()).get("path"));
	}

}
