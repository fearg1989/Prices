package com.Api.Prices.Controllers;

import com.Api.Prices.Models.Prices;
import com.Api.Prices.Services.PricesServices;
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
public class PricesControllers {

    @Autowired
    private PricesServices pricesServices;

    @GetMapping("/find")
    public ResponseEntity<?> getPrices(@RequestParam Long product_id, @RequestParam Long brand_id, @RequestParam String application_date) {
        LocalDateTime date = LocalDateTime.parse(application_date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Prices price = pricesServices.getPrices(product_id, brand_id, date);

        if (price != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("product_id", price.getProduct_id());
            response.put("brand_id", price.getBrand_id());
            response.put("price_list", price.getPrice_list());
            response.put("application_date", application_date);
            response.put("price", price.getPrice());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No price found for given parameters");
    }
}
