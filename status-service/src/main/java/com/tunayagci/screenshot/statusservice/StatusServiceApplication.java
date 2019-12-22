package com.tunayagci.screenshot.statusservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableDiscoveryClient
@EnableKafka
public class StatusServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatusServiceApplication.class, args);
    }

}
