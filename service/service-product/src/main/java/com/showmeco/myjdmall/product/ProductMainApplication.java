package com.showmeco.myjdmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/3 1:04
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication

@MapperScan("com.showmeco.myjdmall.product.mapper")
public class ProductMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductMainApplication.class, args);
    }
}
