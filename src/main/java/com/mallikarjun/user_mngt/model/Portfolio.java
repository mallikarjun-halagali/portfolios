package com.mallikarjun.user_mngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("portfolios_sql")
public record Portfolio(

        @Id
        Long id,

        @Column("user_id")
        Long userId,

        @Column("name")
        String name,

        @Column("description")
        String description,

        @Column("created_at")
        LocalDateTime createdAt,

        @Column("updated_at")
        LocalDateTime updatedAt
) {}

