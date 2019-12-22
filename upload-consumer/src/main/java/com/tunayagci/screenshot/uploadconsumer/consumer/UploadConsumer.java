package com.tunayagci.screenshot.uploadconsumer.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.upload.UploadImageRequestEvent;
import com.tunayagci.screenshot.eventregistry.feign.AddImageDTO;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import com.tunayagci.screenshot.uploadconsumer.feignclient.ImageClient;
import com.tunayagci.screenshot.uploadconsumer.producer.UploadResultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UploadConsumer {
    private static final Logger logger = LoggerFactory.getLogger(UploadConsumer.class);

    private ImageClient imageClient;
    private UploadResultProducer producer;

    public UploadConsumer(ImageClient imageClient, UploadResultProducer producer) {
        this.imageClient = imageClient;
        this.producer = producer;
    }

    @KafkaListener(id = "image-consumers",
            topics = Topics.IMAGE_UPLOAD_REQUEST,
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void consume(UploadImageRequestEvent event) throws ExecutionException, InterruptedException {
        logger.info(event.toString());
        final String scanId = event.getScanId();
        final String url = event.getUrl();
        try {
            imageClient.uploadImage(new AddImageDTO(scanId, url, event.getBase64Image()));
        } catch (Exception e) {
            logger.error("Error uploading image", e);
            producer.failedUpload(scanId, scanId, e.getMessage(), url);
            return;
        }
        producer.successfullyUploaded(scanId, scanId, url);
    }
}
