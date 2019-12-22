package com.tunayagci.screenshot.screenshotconsumer.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanRegisteredEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import com.tunayagci.screenshot.screenshotconsumer.producer.ScreenshotStatusProducer;
import com.tunayagci.screenshot.screenshotconsumer.producer.UploadImageProducer;
import com.tunayagci.screenshot.screenshotconsumer.service.ScreenshotCaptureService;
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
    private ScreenshotStatusProducer screenshotStatusProducer;
    private ScreenshotCaptureService screenshotCaptureService;

    public ScreenshotConsumer(UploadImageProducer uploadImageProducer, ScreenshotStatusProducer screenshotStatusProducer, ScreenshotCaptureService screenshotCaptureService) {
        this.uploadImageProducer = uploadImageProducer;
        this.screenshotStatusProducer = screenshotStatusProducer;
        this.screenshotCaptureService = screenshotCaptureService;
    }

    @KafkaListener(id = "webclient-consumers",
            topics = Topics.SCREENSHOT_REQUEST,
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void consumeMessage(ScanRegisteredEvent scanRegisteredEvent) throws ExecutionException, InterruptedException {
        logger.info(scanRegisteredEvent.toString());
        final String scanId = scanRegisteredEvent.getScanId();
        final String url = scanRegisteredEvent.getUrl();
        String base64Image;
        try {
            base64Image = screenshotCaptureService.getScreenshot();
        } catch (Exception e) {
            logger.error("Cannot get screenshot", e);
            screenshotStatusProducer.failEvent(scanId, scanId, e.getMessage(), url);
            return;
        }
        screenshotStatusProducer.successEvent(scanId, scanId, url);
        uploadImageProducer.uploadRequest(UUID.randomUUID().toString(),
                scanId, base64Image, url);
    }
}
