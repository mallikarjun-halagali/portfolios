package com.mallikarjun.portfolios.model.request;

import com.mallikarjun.portfolios.model.Stocks;
import lombok.Data;

import java.util.List;

@Data
public class StocksRequest {

    private String isin;
    private String name;
    private Integer quantity;
    private Double investedValue;

}
