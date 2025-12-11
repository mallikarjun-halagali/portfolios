package com.mallikarjun.portfolios.controller;

import com.mallikarjun.portfolios.model.Portfolio;
import com.mallikarjun.portfolios.model.request.PortfolioRequest;
import com.mallikarjun.portfolios.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/create")
    public Mono<Portfolio> createPortfolio(@RequestBody PortfolioRequest portfolio) {
        System.out.println("Received portfolio creation request: " + portfolio);
        return portfolioService.createPortfolio(portfolio);
    }

    @GetMapping("/{id}")
    public Mono<Portfolio> getPortfolio(@PathVariable String portfolioId) {
        return portfolioService.getPortfolioById(portfolioId);
    }

    @GetMapping("/user/{userId}")
    public Mono<Portfolio> getPortfoliosByUserId(@PathVariable String userId){
        return portfolioService.getPortfoliosByUserId(userId);
    }


}
