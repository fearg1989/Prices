package com.Api.Prices.Infrastructure.Repository;

import com.Api.Prices.Domain.Model.Prices;
import com.Api.Prices.Domain.Port.PricesRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceJpaRepository implements PricesRepository {

    private final SpringDataPriceRepository repository;

    public PriceJpaRepository(SpringDataPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Prices> findPrice(Long productId, Long brandId, LocalDateTime applicationDate){
        return repository.findPrice(productId, brandId, applicationDate);
    }

}
