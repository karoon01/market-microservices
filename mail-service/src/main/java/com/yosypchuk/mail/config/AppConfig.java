package com.yosypchuk.mail.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Queue emailQueue() {
        return new Queue("emailQueue", false);
    }
}
