package com.mallikarjun.portfolios.controller;

import com.mallikarjun.portfolios.model.Portfolio;
import org.springframework.ui.Model;
import com.mallikarjun.portfolios.model.request.PortfolioRequest;
import com.mallikarjun.portfolios.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Controller
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioViewController {

    private final PortfolioService portfolioService;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("portfolio", new PortfolioRequest());
        return "portfolio-create";
    }

//    @PostMapping("/create")
//    public String createPortfolio(@ModelAttribute PortfolioRequest request) {
//
//        Portfolio portfolio = portfolioService
//                .createPortfolio(request)
//                .block(); // UI layer only
//
//        return "redirect:/portfolio/" + portfolio.getPortfolioId();
//    }

    @PostMapping("/create")
    public Mono<String> createPortfolio(@ModelAttribute PortfolioRequest request) {

        return portfolioService
                .createPortfolio(request)
                .map(portfolio ->
                        "redirect:/portfolio/" + portfolio.getPortfolioId()
                );
    }
//    @GetMapping("/{portfolioId}")
//    public String viewPortfolio(@PathVariable String portfolioId, Model model) {
//
//        Portfolio portfolio = portfolioService
//                .getPortfolioById(portfolioId)
//                .block();
//
//        model.addAttribute("portfolio", portfolio);
//        return "portfolio-view";
//    }

    @GetMapping("/{portfolioId}")
    public Mono<String> viewPortfolio(@PathVariable String portfolioId, Model model) {

        return portfolioService
                .getPortfolioById(portfolioId)
                .map(portfolio -> {
                    model.addAttribute("portfolio", portfolio);
                    return "portfolio-view";
                });
    }

    @GetMapping("/{id}/edit")
    public String editPortfolio(@PathVariable String id, Model model) {
        Mono<Portfolio> portfolio = portfolioService.getPortfolioById(id);
        model.addAttribute("portfolio", portfolio);
        return "edit-portfolio";
    }

    @PostMapping("/update")
    public Mono<String> updatePortfolio(@ModelAttribute Portfolio portfolio) {
        return portfolioService.updatePortfolio(portfolio);
//        return "redirect:/portfolio/" + portfolio.getPortfolioId();
    }

}
