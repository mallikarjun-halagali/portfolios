package com.mallikarjun.user_mngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("user_notification_channels")
public record UserNotificationChannel(

        @Id
        Long id,

        @Column("user_id")
        Long userId,

        @Column("type")
        String type,

        @Column("address")
        String address,

        @Column("is_verified")
        Boolean isVerified,

        @Column("is_primary")
        Boolean isPrimary,

        @Column("created_at")
        LocalDateTime createdAt,

        @Column("updated_at")
        LocalDateTime updatedAt
) {}

