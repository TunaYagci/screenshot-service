package com.tunayagci.screenshot.scanservice.producer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanQueuedEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class ScanRequestProducer {

    private KafkaTemplate<String, ScanQueuedEvent> producer;

    public ScanRequestProducer(KafkaTemplate<String, ScanQueuedEvent> producer) {
        this.producer = producer;
    }

    public SendResult<String, ScanQueuedEvent> scanRequest(String key, String scanId, String url) throws ExecutionException, InterruptedException {
        return producer.send(Topics.SCREENSHOT_REQUEST, key, new ScanQueuedEvent(scanId, new Date(), url))
                .get();
    }
}
