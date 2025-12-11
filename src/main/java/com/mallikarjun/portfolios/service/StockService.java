package com.mallikarjun.portfolios.service;

import com.mallikarjun.portfolios.model.FinHubStocks;
import com.mallikarjun.portfolios.model.Stocks;
import com.mallikarjun.portfolios.model.request.StocksRequest;
import com.mallikarjun.portfolios.repository.FinHubRepository;
import com.mallikarjun.portfolios.service.externalAPI.FinHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class StockService {
    private final FinHubService finHubService;
    private final FinHubRepository finHubRepository;

    public List<Stocks> updateStocks(StocksRequest stockRequest) {


        return null;
    }

    public Flux<FinHubStocks> featchAllStocks() {
        AtomicInteger count = new AtomicInteger();

        return finHubRepository.saveAll(finHubService.getAllStocks());



    }
}
