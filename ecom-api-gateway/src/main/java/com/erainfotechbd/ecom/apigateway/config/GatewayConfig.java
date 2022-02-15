package com.erainfotechbd.ecom.apigateway.config;

import com.google.inject.internal.cglib.proxy.$Factory;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.Customizer;

//@Configuration
public class GatewayConfig {

    //@Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
       return builder.routes()
               .route(p ->  p
                       .path("/v1/user/")
                       .filters(f->f.circuitBreaker(c->c.setName("user").setFallbackUri("/userFallBack")))
                       .uri("http://10.11.201.180:9092")
               )
               .route(p ->  p
                       .path("/v1/product/")
                       .uri("http://10.11.201.180:9093")
               )
               .route(p ->  p
                       .path("/v1/order/")
                       .uri("http://10.11.201.180:9094")
               )
               .route(p ->  p
                       .path("/v1/clint/")
                       .uri("http://10.11.201.180:9095")
               )
               .build();
    }

}
