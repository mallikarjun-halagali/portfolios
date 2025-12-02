package com.mallikarjun.user_mngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("user_profile")
public record UserProfile(

        @Id
        @Column("user_id")
        Long userId,

        @Column("dob")
        LocalDate dob,

        @Column("country")
        String country,

        @Column("risk_profile")
        String riskProfile,

        @Column("investment_horizon_years")
        Integer investmentHorizonYears,

        @Column("created_at")
        LocalDateTime createdAt,

        @Column("updated_at")
        LocalDateTime updatedAt
) {}
