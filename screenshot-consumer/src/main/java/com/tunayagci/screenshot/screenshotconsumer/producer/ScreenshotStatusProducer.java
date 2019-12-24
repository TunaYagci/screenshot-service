package com.tunayagci.screenshot.screenshotconsumer.producer;

import com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotCompletedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotFailedEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class ScreenshotStatusProducer {

    private KafkaTemplate<String, ScreenshotCompletedEvent> successProducer;
    private KafkaTemplate<String, ScreenshotFailedEvent> failProducer;

    public ScreenshotStatusProducer(KafkaTemplate<String, ScreenshotCompletedEvent> successProducer, KafkaTemplate<String, ScreenshotFailedEvent> failProducer) {
        this.successProducer = successProducer;
        this.failProducer = failProducer;
    }

    public SendResult<String, ScreenshotCompletedEvent> successEvent(String key, String scanId, String url, String imageURL) throws ExecutionException, InterruptedException {
        return successProducer.send(Topics.SCREENSHOT_STATUS, key, new ScreenshotCompletedEvent(scanId, new Date(), url))
                .get();
    }

    public SendResult<String, ScreenshotFailedEvent> failEvent(String key, String scanId, String errorMessage, String url) throws ExecutionException, InterruptedException {
        return failProducer.send(Topics.SCREENSHOT_STATUS, key, new ScreenshotFailedEvent(scanId, new Date(), errorMessage, url))
                .get();
    }
}
