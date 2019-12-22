package com.tunayagci.screenshot.uploadconsumer.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.upload.UploadImageRequestEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UploadConsumer {
    private static final Logger logger = LoggerFactory.getLogger(UploadConsumer.class);

    @KafkaListener(id = "image-consumers",
            topics = Topics.IMAGE_UPLOAD_REQUEST,
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void consume(UploadImageRequestEvent event) {
        logger.info(event.toString());
    }
}
