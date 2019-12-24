package com.tunayagci.screenshot.scanservice.producer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanFailedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanStartedEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ScanStatusProducer {

    private KafkaTemplate<String, ScanStartedEvent> scanQueuedProducer;
    private KafkaTemplate<String, ScanFailedEvent> scanFailedToBeQueuedProducer;

    public ScanStatusProducer(KafkaTemplate<String, ScanStartedEvent> scanQueuedProducer, KafkaTemplate<String, ScanFailedEvent> scanFailedToBeQueuedProducer) {
        this.scanQueuedProducer = scanQueuedProducer;
        this.scanFailedToBeQueuedProducer = scanFailedToBeQueuedProducer;
    }

    public SendResult<String, ScanStartedEvent> scanStartedEvent(String key, String scanId, List<String> urls) throws ExecutionException, InterruptedException {
        return scanQueuedProducer.send(Topics.SCAN_STATUS, key, new ScanStartedEvent(scanId, new Date(), urls))
                .get();
    }

    public SendResult<String, ScanFailedEvent> scanFailedEvent(String key, String scanId, String url, String errorMessage) throws ExecutionException, InterruptedException {
        return scanFailedToBeQueuedProducer.send(Topics.SCAN_STATUS, key, new ScanFailedEvent(scanId, new Date(), url, errorMessage))
                .get();
    }
}
