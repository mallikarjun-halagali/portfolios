package com.mallikarjun.portfolios.controller;

import com.mallikarjun.portfolios.model.PortfolioProfile;
import com.mallikarjun.portfolios.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PortfolioConntroller {

    private final PortfolioService portfolioService;

    @GetMapping("get/all")
    public Flux<?> getAllPortfolios() {
        return portfolioService.getAllPortfolios();

    }

    @PostMapping("/create")
    public Mono<?> createPortfolio(@RequestBody PortfolioProfile portfolioProfile) {
        return portfolioService.createPortfolio(portfolioProfile);
//        return Mono.just("Portfolio created");
    }
}
