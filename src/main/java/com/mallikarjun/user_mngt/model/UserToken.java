package com.mallikarjun.user_mngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("user_tokens")
public record UserToken(

        @Id
        Long id,

        @Column("user_id")
        Long userId,

        @Column("token")
        String token,

        @Column("type")
        String type,

        @Column("expires_at")
        LocalDateTime expiresAt,

        @Column("used_at")
        LocalDateTime usedAt,

        @Column("created_at")
        LocalDateTime createdAt
) {}
