package com.mallikarjun.user_mngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("users")
public record User(
        @Id
        Long id,

        @Column("email")
        String email,

        @Column("phone_number")
        String phoneNumber,

        @Column("password_hash")
        String passwordHash,

        @Column("full_name")
        String fullName,

        @Column("status")
        String status,

        @Column("timezone")
        String timezone,

        @Column("preferred_currency")
        String preferredCurrency,

        @Column("created_at")
        LocalDateTime createdAt,

        @Column("updated_at")
        LocalDateTime updatedAt
) {}

