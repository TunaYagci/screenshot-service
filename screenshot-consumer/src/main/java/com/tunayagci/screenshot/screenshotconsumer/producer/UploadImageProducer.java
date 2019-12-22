package com.tunayagci.screenshot.screenshotconsumer.producer;

import com.tunayagci.screenshot.eventregistry.event.scan.upload.UploadImageRequestEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class UploadImageProducer {

    private KafkaTemplate<String, UploadImageRequestEvent> producer;

    public UploadImageProducer(KafkaTemplate<String, UploadImageRequestEvent> producer) {
        this.producer = producer;
    }

    public SendResult<String, UploadImageRequestEvent> uploadRequest(String key, String scanId, String image, String url) throws ExecutionException, InterruptedException {
        return producer.send(Topics.IMAGE_UPLOAD_REQUEST, key, new UploadImageRequestEvent(scanId, new Date(), image, url))
                .get();
    }
}
