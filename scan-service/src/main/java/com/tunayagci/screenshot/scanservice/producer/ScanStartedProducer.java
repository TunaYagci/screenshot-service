package com.tunayagci.screenshot.scanservice.producer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanStartedEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ScanStartedProducer {

    private KafkaTemplate<String, ScanStartedEvent> producer;

    public ScanStartedProducer(KafkaTemplate<String, ScanStartedEvent> producer) {
        this.producer = producer;
    }

    public SendResult<String, ScanStartedEvent> scanStartedEvent(String key, String scanId, List<String> urls) throws ExecutionException, InterruptedException {
        return producer.send(Topics.SCAN_STATUS, key, new ScanStartedEvent(scanId, new Date(), urls))
                .get();
    }
}
