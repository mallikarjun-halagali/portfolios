package com.mallikarjun.portfolios.service;

import com.mallikarjun.portfolios.model.Portfolio;
import com.mallikarjun.portfolios.model.Stocks;
import com.mallikarjun.portfolios.model.request.PortfolioRequest;
import com.mallikarjun.portfolios.repository.PortfolioRepository;
import com.mallikarjun.portfolios.service.externalAPI.FinHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static reactor.core.publisher.Flux.just;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final FinHubService finHubService;


    public Mono<Portfolio> createPortfolio(PortfolioRequest portfolioRequest) {

        Portfolio portfolio = new Portfolio();

        portfolio.setPortfolioId(portfolioRequest.getUserId()+"-"+portfolioRequest.getPortfolioName());
        portfolio.setName(portfolioRequest.getPortfolioName());
        portfolio.setUserId(portfolioRequest.getUserId());

        portfolio.setStocksList(buildStocksList(portfolioRequest, portfolio));

        portfolio.setInvestedValue(calculateInvestedValue(portfolio));

        return portfolioRepository.save(portfolio);

    }

    private Double calculateInvestedValue(Portfolio portfolio) {
        return portfolio.getStocksList().stream().mapToDouble(Stocks::getInvestedValue).sum();
    }


    private List<Stocks> buildStocksList(PortfolioRequest portfolioRequest, Portfolio portfolio) {
        return portfolioRequest.getStocks().stream()
                .map(stocksRequest -> {
                    Stocks stock = new Stocks();
                    stock.setIsin(stocksRequest.getIsin());
                    stock.setName(stocksRequest.getName());
                    stock.setQuantity(stocksRequest.getQuantity());
                    stock.setInvestedValue(stocksRequest.getInvestedValue());
                    stock.setAveragePrice(stocksRequest.getInvestedValue() / stocksRequest.getQuantity());
                    return stock;
                }).toList();
    }

    public Mono<Portfolio> getPortfolioById(String portfolioId) {
        return portfolioRepository.findByPortfolioId(portfolioId);
    }

    public Mono<Portfolio> getPortfoliosByUserId(String userId) {
        return portfolioRepository.getByUserId(userId);
    }

    public Mono<?> getDailyPerformance(String portfolioId) {
        Mono<Portfolio> portfolioById = getPortfolioById(portfolioId);
        portfolioById.subscribe(portfolio -> {
            portfolio.getStocksList().forEach(stock -> {
                finHubService.stockQuote(stock.getIsin()).subscribe(response -> {
                    System.out.println("Stock data for ISIN " + stock.getIsin() + ": " + response.toString());
                });
            });
        });

        return Mono.just("Daily performance data fetch initiated for portfolio: " + portfolioId);


    }
}
