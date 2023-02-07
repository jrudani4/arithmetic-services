package com.arithmeticservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    private static final String JWT_SECURITY = "lb://jwt-security";

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/v1/auth/register").uri(JWT_SECURITY))
                .route(p -> p.path("/api/v1/auth/authenticate").uri(JWT_SECURITY))
                .route(p -> p.path("/arithmetic/addition/**").uri("lb://addition-service"))
                .route(p -> p.path("/arithmetic/multiply/**").uri("lb://multiplication-service"))
                .route(p -> p.path("/arithmetic/division/**").uri("lb://division-service"))
                .route(p -> p.path("/arithmetic/subtraction/**").uri("lb://subtraction-service"))
                .route(p -> p.path("/users/view-all").uri(JWT_SECURITY))
                .route(p -> p.path("/users/delete/**").uri(JWT_SECURITY))
                .route(p -> p.path("/users/update/**").uri(JWT_SECURITY))
                .build();
    }
}
