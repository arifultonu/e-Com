package com.erainfotechbd.ecom.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcomApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomApiGatewayApplication.class, args);
	}

}


/*
	filters:
            - name: SurcuitBreaker
              args:
                name: DEPARTMENT-SERVICE
                fallbackuri: forward:/departmentServiceFallback


          filters:
            - name: SurcuitBreaker
              args:
                name: USER-SERVICE
                fallbackuri: forward:/userServiceFallback


                <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>


hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 100000
	 */




	/*
	eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://10.11.201.180:9090/eureka/

  instance:
    hostname: 10.11.201.180
	 */

	/*
	spring:
  application:
    name: API-GATEWAY
  cloud:
    gateway:
      routes:
        - id: DEPARTMENT-SERVICE
          uri: lb://DEPARTMENT-SERVICE
          predicates:
            - Path=/department/**
        - id: USER-SERVICE
          uri: lb://USER-SERVICE
          predicates:
            - Path=/users/**
	 */
