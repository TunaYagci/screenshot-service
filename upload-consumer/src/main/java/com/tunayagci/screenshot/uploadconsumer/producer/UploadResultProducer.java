package com.tunayagci.screenshot.uploadconsumer.producer;

import com.tunayagci.screenshot.eventregistry.event.scan.upload.UploadCompletedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.upload.UploadFailedEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class UploadResultProducer {

    private KafkaTemplate<String, UploadCompletedEvent> successProducer;
    private KafkaTemplate<String, UploadFailedEvent> failProducer;

    public UploadResultProducer(KafkaTemplate<String, UploadCompletedEvent> successProducer, KafkaTemplate<String, UploadFailedEvent> failProducer) {
        this.successProducer = successProducer;
        this.failProducer = failProducer;
    }

    public SendResult<String, UploadCompletedEvent> successfullyUploaded(String key, String scanId, String image, String url) throws ExecutionException, InterruptedException {
        return successProducer.send(Topics.UPLOAD_IMAGE_STATUS, key, new UploadCompletedEvent(scanId, new Date(), image, url))
                .get();
    }

    public SendResult<String, UploadFailedEvent> failedUpload(String key, String scanId, String errorMessage, String url) throws ExecutionException, InterruptedException {
        return failProducer.send(Topics.UPLOAD_IMAGE_STATUS, key, new UploadFailedEvent(scanId, new Date(), errorMessage, url))
                .get();
    }
}
