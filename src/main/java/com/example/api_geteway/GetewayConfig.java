package com.example.api_geteway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Маршруты для микросервиса Cars
                .route("car_route", r -> r
                        .path("/cars/**")
                        .uri("lb://carservice"))

                // Маршруты для микросервиса Drivers
                .route("driver_route", r -> r
                .path("/drivers/**")
                .uri("lb://driverservice"))

                .build();
    }
}
