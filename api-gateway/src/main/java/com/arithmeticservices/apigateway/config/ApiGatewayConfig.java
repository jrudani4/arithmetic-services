package com.arithmeticservices.apigateway.config;

import com.arithmeticservices.apigateway.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    private static final String JWT_SECURITY = "lb://jwt-security";

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/v1/auth/**").filters(f->f.filter(filter)).uri(JWT_SECURITY))
//                .route(p -> p.path("/api/v1/auth/authenticate").filters(f->f.filter(filter)).uri(JWT_SECURITY))
                .route(p -> p.path("/arithmetic/addition/**").filters(f->f.filter(filter)).uri("lb://addition-service"))
                .route(p -> p.path("/arithmetic/multiply/**").filters(f->f.filter(filter)).uri("lb://multiplication-service"))
                .route(p -> p.path("/arithmetic/division/**").filters(f->f.filter(filter)).uri("lb://division-service"))
                .route(p -> p.path("/arithmetic/subtraction/**").filters(f->f.filter(filter)).uri("lb://subtraction-service"))
                .route(p -> p.path("/users/view-all").filters(f->f.filter(filter)).uri(JWT_SECURITY))
                .route(p -> p.path("/users/delete/**").filters(f->f.filter(filter)).uri(JWT_SECURITY))
                .route(p -> p.path("/users/update/**").filters(f->f.filter(filter)).uri(JWT_SECURITY))
                .route(p -> p.path("/users/view/**").filters(f->f.filter(filter)).uri(JWT_SECURITY))
                .build();
    }
}
