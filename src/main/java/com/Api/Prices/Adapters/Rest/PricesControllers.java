package com.Api.Prices.Adapters.Rest;

import com.Api.Prices.Application.Dto.PricesDTO;
import com.Api.Prices.Domain.Port.PricesServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prices")
@Tag(name = "Prices API")
public class PricesControllers {

    @Autowired
    private PricesServices pricesServices;

    @GetMapping("/find")
    @Operation(summary = "Get price by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prices found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"brand_id\": 1, \"product_id\": 35455, \"price_list\": 1, \"price\": 35.50, \"application_date\": \"2020-06-14 10:00:00\"}"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"status\": 400, \"error\": \"Bad Request\", \"message\": \"For input string: \\\"f\\\"\", \"timestamp\": \"2025-05-22T15:30:45\", \"path\": \"/api/prices/find\" }"))),
            @ApiResponse(responseCode = "404", description = "Prices not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"status\": 404, \"error\": \"Not Found\", \"message\": \"No price found for product: 35455 and brand 3 at 2020-06-14T21:00\", \"timestamp\": \"2025-05-22T15:30:45\", \"path\": \"/api/prices/find\" }")))
    })
    public ResponseEntity<?> getPrices(
            @Parameter(description = "Product ID", example = "35455")
            @RequestParam Long product_id,
            @Parameter(description = "Brand ID", example = "1")
            @RequestParam Long brand_id,
            @Parameter(description = "Application date in format yyyy-MM-dd HH:mm:ss", example = "2020-06-14 10:00:00")
            @RequestParam String application_date) {
        LocalDateTime date = LocalDateTime.parse(application_date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        PricesDTO price = pricesServices.getPrices(product_id, brand_id, date);

        if (price != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("product_id", price.getProductId());
            response.put("brand_id", price.getBrandId());
            response.put("price_list", price.getPriceList());
            response.put("application_date", application_date);
            response.put("price", price.getPrice());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No price found for given parameters");
    }

}
