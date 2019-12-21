package com.tunayagci.screenshot.scanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ScanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScanServiceApplication.class, args);
    }

}
