package com.mallikarjun.portfolios.controller;

import com.mallikarjun.portfolios.model.FinHubStocks;
import com.mallikarjun.portfolios.model.Stocks;
import com.mallikarjun.portfolios.model.request.StocksRequest;
import com.mallikarjun.portfolios.service.StockService;
import com.mallikarjun.portfolios.service.externalAPI.FinHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final FinHubService finHubService;


//    @PostMapping("/add")
//    public String addStock(@RequestBody StocksRequest stockRequest) {
//        // Here you would typically call a service to handle the business logic
//        // For this example, we'll just return a confirmation message
//        List<Stocks> updatedStocks = stockService.updateStocks(stockRequest);
//        return "Stock with ID " + stockRequest.getId() + " added successfully.";
//
//    }


    @GetMapping("/{stockSymbol}")
    public Mono<?> getStockPrices(@PathVariable String stockSymbol) {
        // Here you would typically call a service to handle the business logic
        // For this example, we'll just return a confirmation message
        return finHubService.searchStockSymbol(stockSymbol);
    }

    @GetMapping("/featch/all")
    public Flux<FinHubStocks> featchAllStocks() {

        return stockService.featchAllStocks();
    }

}
