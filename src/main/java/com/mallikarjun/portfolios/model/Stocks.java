package com.mallikarjun.portfolios.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Stocks {

//    @Id
//    private String id;
    private String isin;
    private String name;
    private Integer quantity;
    private Double investedValue;
    private Double averagePrice; // investedValue / quantity
    private Double currentValue;
//    private Double price;
//    private String stockSymbol;

}
