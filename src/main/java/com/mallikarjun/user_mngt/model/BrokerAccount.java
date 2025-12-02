package com.mallikarjun.user_mngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("broker_accounts")
public record BrokerAccount(

        @Id
        Long id,

        @Column("user_id")
        Long userId,

        @Column("provider")
        String provider,

        @Column("external_account_id")
        String externalAccountId,

        @Column("status")
        String status,

        @Column("last_synced_at")
        LocalDateTime lastSyncedAt,

        @Column("created_at")
        LocalDateTime createdAt,

        @Column("updated_at")
        LocalDateTime updatedAt
) {}
