package com.tunayagci.screenshot.statusservice.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanStartedEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ScanStatusConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ScanStatusConsumer.class);

    @KafkaListener(id = "scan-status-consumers",
            topics = Topics.SCAN_STATUS,
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void scanStatus(ScanStartedEvent startedEvent) {
        logger.info(startedEvent.toString());
    }
}
