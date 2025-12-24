package com.mallikarjun.portfolios.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EMAIL_QUEUE = "email.queue";
    public static final String EMAIL_DLX = "email.dlx";
    public static final String EMAIL_DLQ = "email.dlq";

    @Bean
    Queue emailQueue() {
        return QueueBuilder.durable(EMAIL_QUEUE)
                .withArgument("x-dead-letter-exchange", EMAIL_DLX)
                .build();
    }

    @Bean
    DirectExchange dlx() {
        return new DirectExchange(EMAIL_DLX);
    }

    @Bean
    Queue dlq() {
        return QueueBuilder.durable(EMAIL_DLQ).build();
    }

    @Bean
    Binding dlqBinding() {
        return BindingBuilder.bind(dlq())
                .to(dlx())
                .with(EMAIL_QUEUE);
    }
}

