package com.mallikarjun.portfolios.service;

import com.mallikarjun.portfolios.model.Holdings;
import com.mallikarjun.portfolios.repository.HoldingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HoldingsService {
    private final HoldingsRepository holdingsRepository;


    public Mono<?> addHoldings(Holdings holding) {

        return holdingsRepository.save(holding);
    }

    public Flux<?> addAllHoldings(Flux<Holdings> holdingsFlux) {
        return holdingsRepository.saveAll(holdingsFlux);
    }

    public Flux<?> getHoldingsById(int id) {

        return holdingsRepository.findByUserId(id);
    }
}
