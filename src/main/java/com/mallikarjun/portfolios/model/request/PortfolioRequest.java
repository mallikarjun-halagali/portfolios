package com.mallikarjun.portfolios.model.request;

import lombok.Data;

import java.util.List;

@Data
public class PortfolioRequest {
    private String userId;
    private String portfolioName;
    private String portfolioId; // portfolio unique identifier (portfolioName + userId)
    private List<StocksRequest> stocks;
}
