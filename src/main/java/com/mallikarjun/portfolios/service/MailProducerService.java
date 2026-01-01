package com.mallikarjun.portfolios.service;

import com.mallikarjun.portfolios.model.Portfolio;
import com.mallikarjun.portfolios.service.externalAPI.InsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailProducerService {
    private final PortfolioService portfolioService;
    private final InsightService insightService;

    public Object getDailyMailDataAndSend(String portfolioId) {
        Portfolio dayReport =(Portfolio) portfolioService.getDailyPerformance(portfolioId).block();

        assert dayReport != null;
        return dayReport.getStocksList().stream()
                .filter(Objects::nonNull)
                .map(stocks -> {
                    return insightService.getStockInsights(stocks.getName());
                })
                .toList();

    }
}
