package com.mallikarjun.user_mngt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Table("user_notification_preferences")
public record UserNotificationPreferences(

        @Id
        @Column("user_id")
        Long userId,

        @Column("daily_report_enabled")
        Boolean dailyReportEnabled,

        @Column("report_time_local")
        LocalTime reportTimeLocal,

        @Column("report_frequency")
        String reportFrequency,

        @Column("channel_email_enabled")
        Boolean channelEmailEnabled,

        @Column("channel_sms_enabled")
        Boolean channelSmsEnabled,

        @Column("channel_whatsapp_enabled")
        Boolean channelWhatsappEnabled,

        @Column("channel_telegram_enabled")
        Boolean channelTelegramEnabled,

        @Column("last_report_sent_at")
        LocalDateTime lastReportSentAt,

        @Column("created_at")
        LocalDateTime createdAt,

        @Column("updated_at")
        LocalDateTime updatedAt
) {}

