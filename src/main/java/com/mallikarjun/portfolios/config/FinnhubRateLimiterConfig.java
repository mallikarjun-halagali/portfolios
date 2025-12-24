package com.mallikarjun.portfolios.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class FinnhubRateLimiterConfig {

    @Bean
    public RateLimiter finnhubRateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1)                  // 1 request
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ZERO)     // fail fast
                .build();

        return RateLimiter.of("finnhubRateLimiter", config);
    }
}

