package com.tunayagci.screenshot.screenshotconsumer.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanQueuedEvent;
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
            topics = Topics.SCAN_REQUEST,
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void consumeMessage(ScanQueuedEvent scanRegisteredEvent) {
        logger.info(scanRegisteredEvent.toString());
        final String scanId = scanRegisteredEvent.getScanId();
        final String url = scanRegisteredEvent.getUrl();
        byte[] image;

        try {
            image = screenshotCaptureServiceObjectFactory.getObject().getScreenshot(url);
        } catch (Exception e) {
            logger.error("Cannot get screenshot", e);
            try {
                screenshotStatusProducer.failEvent(scanId, scanId, e.getMessage(), url);
            } catch (Exception e2) {
                logger.error("Cannot publish fail event", e2);
            }
            return;
        }

        final String imageURL = imageClient.uploadImage(new AddImageDTO(scanId, url, image));
        try {
            screenshotStatusProducer.successEvent(scanId, scanId, url, imageURL);
        } catch (Exception e) {
            logger.error("Cannot publish success event", e);
        }
    }
}
