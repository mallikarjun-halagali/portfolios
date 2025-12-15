package com.mallikarjun.portfolios.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Portfolio {
    @Id
    private String id;
    private String name;
    private String userId;
    private String portfolioId; // portfolio unique identifier (portfolioName + userId)
    private Double investedValue;
    private Double currentValue;
//    private Double totalValue;
    private Instant lastUpdated;
    private Double dailyChange;
    private Double profitLoss;
    private List<Stocks> stocksList;

    private String dailyChangePercent;
    private String currency = "Rupees";
    //    private Integer sqlPortfolioId;
}
