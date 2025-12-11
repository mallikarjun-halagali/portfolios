package com.mallikarjun.portfolios.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class FinHubStocks {

    @Id
    private String symbol;
    private String currency;
    private String description;
    private String displaySymbol;
    private String figi;
    private String isin;
    private String mic;
    private String shareClassFIGI;
    private String symbol2;
    private String type;

}
