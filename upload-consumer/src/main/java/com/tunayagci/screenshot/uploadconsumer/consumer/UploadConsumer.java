package com.tunayagci.screenshot.uploadconsumer.consumer;

import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UploadConsumer {

    @KafkaListener(id = "image-consumers", topics = Topics.IMAGE_UPLOAD_REQUEST)
    public void consume(String message) {
        System.out.println("Got message= " + message);
    }
}
