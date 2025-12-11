//package com.mallikarjun.portfolios.controller;
//
//import com.mallikarjun.portfolios.model.Holdings;
//import com.mallikarjun.portfolios.service.HoldingsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequiredArgsConstructor
//public class HoldingsController {
//
//    private final HoldingsService holdingsService;
//
//    @PostMapping("/holdings/add" )
//    public Mono<?> addHoldings(@RequestBody Holdings holding) {
//        // Implementation for adding holdings will go here
//        return holdingsService.addHoldings(holding);
//    }
//
//    @PostMapping("/holdings/add/all" )
//    public Flux<?> addAllHoldings(@RequestBody Flux<Holdings> holdingsFlux) {
//        // Implementation for adding multiple holdings will go here
//        return holdingsService.addAllHoldings(holdingsFlux);
//    }
//
//    @GetMapping("/holdings/get/{id}")
//    public Flux<?> getHoldingsById(@PathVariable int id) {
//        // Implementation for retrieving holdings by ID will go here
//        return holdingsService.getHoldingsById(id);
//    }
//
//}
