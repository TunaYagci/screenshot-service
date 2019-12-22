package com.tunayagci.screenshot.screenshotconsumer.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanRegisteredEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import com.tunayagci.screenshot.screenshotconsumer.producer.UploadImageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ScreenshotConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotConsumer.class);

    private UploadImageProducer uploadImageProducer;

    public ScreenshotConsumer(UploadImageProducer uploadImageProducer) {
        this.uploadImageProducer = uploadImageProducer;
    }

    @KafkaListener(id = "webclient-consumers",
            topics = Topics.SCREENSHOT_REQUEST,
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void consumeMessage(ScanRegisteredEvent scanRegisteredEvent) throws ExecutionException, InterruptedException {
        logger.info(scanRegisteredEvent.toString());
        String image = "image-" + UUID.randomUUID().toString();
        uploadImageProducer.uploadRequest(UUID.randomUUID().toString(),
                scanRegisteredEvent.getScanId(), image, scanRegisteredEvent.getUrl());
    }
}
