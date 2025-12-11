package com.mallikarjun.portfolios.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FinHubStockQuote {
    /** Current price */
    @JsonProperty("c")
    private double currentPrice;

    /** Change */
    @JsonProperty("d")
    private double change;

    /** Percent change */
    @JsonProperty("dp")
    private double percentChange;

    /** High price of the day */
    @JsonProperty("h")
    private double highPrice;

    /** Low price of the day */
    @JsonProperty("l")
    private double lowPrice;

    /** Open price of the day */
    @JsonProperty("o")
    private double openPrice;

    /** Previous close price */
    @JsonProperty("pc")
    private double previousClosePrice;
}
