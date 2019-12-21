package com.tunayagci.screenshot.screenshotconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ScreenshotConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScreenshotConsumerApplication.class, args);
    }

}
