package com.mallikarjun.portfolios.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Document(collection = "portfolios_profile")
public class PortfolioProfile {
        @Id
        private String id;

        private Integer sqlPortfolioId;
        private Integer userId;

        private String name;
        private List<String> tags;
        private String currency;

        private Instant createdAt;
        private Instant updatedAt;

        private Meta meta;
}
