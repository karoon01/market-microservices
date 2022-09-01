package com.yosypchuk.gateway.config;

import com.yosypchuk.gateway.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/user/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("https://localhost:8082"))
                .route("product-service", r -> r.path("/product/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("https://localhost:8083"))
                .route("order-service", r -> r.path("/order/**").and().path("/cart/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("https://localhost:8084"))
                .route("wishlist-service", r -> r.path("/wishlist/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("https://localhost:8085"))
                .build();
    }
}
