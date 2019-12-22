package com.tunayagci.screenshot.screenshotconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableKafka
public class ScreenshotConsumerApplication {

    @Value("${chrome.path}")
    private String chromePath;

    public static void main(String[] args) {
        SpringApplication.run(ScreenshotConsumerApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        System.setProperty("webdriver.chrome.driver", chromePath);
    }
}
