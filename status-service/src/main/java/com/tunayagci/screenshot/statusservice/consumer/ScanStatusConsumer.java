package com.tunayagci.screenshot.statusservice.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanFailedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanQueuedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanStartedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotCompletedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotFailedEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import com.tunayagci.screenshot.statusservice.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ScanStatusConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ScanStatusConsumer.class);

    private StatusService statusService;

    public ScanStatusConsumer(StatusService statusService) {
        this.statusService = statusService;
    }

    @KafkaListener(id = "scan-status-consumers",
            topics = Topics.SCAN_STATUS,
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void scanStatus(ScanEvent scanEvent) {
        logger.info(scanEvent.toString());

        if (scanEvent instanceof ScanStartedEvent) {
            statusService.scanStarted((ScanStartedEvent) scanEvent);
        } else if (scanEvent instanceof ScanFailedEvent) {
            statusService.scanFailed((ScanFailedEvent) scanEvent);
        } else if (scanEvent instanceof ScanQueuedEvent) {
            statusService.scanQueued((ScanQueuedEvent) scanEvent);
        } else if (scanEvent instanceof ScreenshotCompletedEvent) {
            statusService.screenshotCompleted((ScreenshotCompletedEvent) scanEvent);
        } else if (scanEvent instanceof ScreenshotFailedEvent) {
            statusService.screenshotFailed((ScreenshotFailedEvent) scanEvent);
        } else {
            throw new IllegalArgumentException("Unknown scan type: " + scanEvent.toString());
        }
    }
}
