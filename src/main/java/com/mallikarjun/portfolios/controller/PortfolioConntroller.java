package com.mallikarjun.portfolios.controller;

import com.mallikarjun.portfolios.model.PortfolioProfile;
import com.mallikarjun.portfolios.service.PortfolioService;
import com.mallikarjun.portfolios.service.externalAPI.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PortfolioConntroller {

    private final PortfolioService portfolioService;
    private final StockService stockService;

    @GetMapping("get/all")
    public Flux<?> getAllPortfolios() {
        return portfolioService.getAllPortfolios();

    }

    @PostMapping("/create")
    public Mono<?> createPortfolio(@RequestBody PortfolioProfile portfolioProfile) {
        return portfolioService.createPortfolio(portfolioProfile);
//        return Mono.just("Portfolio created");
    }

    @GetMapping("/stock/quote/{symbol}")
    public Mono<?> getStockQuote(@PathVariable String symbol) {
        System.out.println("Fetching stock quote for symbol: " + symbol);
        return stockService.getStockQuote(symbol);
    }
}
