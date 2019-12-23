package com.tunayagci.screenshot.screenshotconsumer.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanRegisteredEvent;
import com.tunayagci.screenshot.eventregistry.feign.AddImageDTO;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import com.tunayagci.screenshot.screenshotconsumer.feignclient.ImageClient;
import com.tunayagci.screenshot.screenshotconsumer.producer.ScreenshotStatusProducer;
import com.tunayagci.screenshot.screenshotconsumer.service.ScreenshotCaptureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ScreenshotConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotConsumer.class);

    private ImageClient imageClient;
    private ScreenshotStatusProducer screenshotStatusProducer;
    private ObjectFactory<ScreenshotCaptureService> screenshotCaptureServiceObjectFactory;

    public ScreenshotConsumer(ImageClient imageClient, ScreenshotStatusProducer screenshotStatusProducer, ObjectFactory<ScreenshotCaptureService> screenshotCaptureServiceObjectFactory) {
        this.imageClient = imageClient;
        this.screenshotStatusProducer = screenshotStatusProducer;
        this.screenshotCaptureServiceObjectFactory = screenshotCaptureServiceObjectFactory;
    }

    @KafkaListener(id = "webclient-consumers",
            topics = Topics.SCREENSHOT_REQUEST,
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void consumeMessage(ScanRegisteredEvent scanRegisteredEvent) throws ExecutionException, InterruptedException {
        logger.info(scanRegisteredEvent.toString());
        final String scanId = scanRegisteredEvent.getScanId();
        final String url = scanRegisteredEvent.getUrl();
        byte[] image;
        try {
            image = screenshotCaptureServiceObjectFactory.getObject().getScreenshot();
        } catch (Exception e) {
            logger.error("Cannot get screenshot", e);
            screenshotStatusProducer.failEvent(scanId, scanId, e.getMessage(), url);
            return;
        }
        imageClient.uploadImage(new AddImageDTO(scanId, url, image));
        screenshotStatusProducer.successEvent(scanId, scanId, url);
    }
}
