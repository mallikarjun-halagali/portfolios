package com.mallikarjun.portfolios.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyPerformanceResponse {
    private double investedValue;
    private double currentValue;
    private double dailyChange;
    private double dailyChangePercent;
}
