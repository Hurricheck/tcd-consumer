package com.deliveryhero.example.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresConfig {
    @Value("${POSTGRES_JDBC_URL}")
    private String postgresJdbcUrl;

    @Value("${POSTGRES_USERNAME}")
    private String postgresUsername;

    @Value("${POSTGRES_PASSWORD}")
    private String  postgresPassword;

    @Bean
    public String postgresJdbcUrl() {
        return postgresJdbcUrl;
    }

    @Bean
    public String postgresUsername() {
        return postgresUsername;
    }

    @Bean
    public String postgresPassword() {
        return postgresPassword;
    }
}
