package com.showmeco.myjsmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/3 20:48
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public class GatewayMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApplication.class, args);
    }
}
