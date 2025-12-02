package com.mallikarjun.user_mngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("user_login_audit")
public record UserLoginAudit(

        @Id
        Long id,

        @Column("user_id")
        Long userId,

        @Column("login_time")
        LocalDateTime loginTime,

        @Column("ip_address")
        String ipAddress,

        @Column("user_agent")
        String userAgent,

        @Column("success")
        Boolean success
) {}
