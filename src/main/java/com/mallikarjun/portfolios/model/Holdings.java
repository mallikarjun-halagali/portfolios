package com.mallikarjun.portfolios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "holdings")
public class Holdings {

        @Id
        private String id;                   // _id: ObjectId(...)
        private String portfolioId;          // portfolioId: userId + portfoliosName
        private Integer userId;              // userId: 45
        private String symbol;               // "TCS"
        private Exchange exchange;           // NSE/BSE/NASDAQ
        private Integer quantity;            // 100
        private Double avgBuyPrice;          // 3200.50
        private Double realizedPnL;          // 0.0
        private Double investedAmount;       // 320050.00
        private Double currentValue;        // 330000.00
        private Double unrealizedPnL;        // nullable
        private Double lastPrice;            // 3300.00
        private Instant lastUpdated;         // ISODate("2025-11-28T09:01:00Z")
        private String sector;               // "IT"
        private List<String> tags;           // ["equity", "bluechip"]

        @Override
        public String toString() {
                return "Holdings{" +
                        "id='" + id + '\'' +
                        ", portfolioId='" + portfolioId + '\'' +
                        ", userId=" + userId +
                        ", symbol='" + symbol + '\'' +
                        ", exchange=" + exchange +
                        ", quantity=" + quantity +
                        ", avgBuyPrice=" + avgBuyPrice +
                        ", realizedPnL=" + realizedPnL +
                        ", unrealizedPnL=" + unrealizedPnL +
                        ", lastPrice=" + lastPrice +
                        ", lastUpdated=" + lastUpdated +
                        ", sector='" + sector + '\'' +
                        ", tags=" + tags +
                        '}';
        }
}
