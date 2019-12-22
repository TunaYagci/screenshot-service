package com.tunayagci.screenshot.screenshotconsumer.consumer;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanRegisteredEvent;
import com.tunayagci.screenshot.eventregistry.topic.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ScreenshotConsumer {
    private Logger logger = LoggerFactory.getLogger(ScreenshotConsumer.class);

    @KafkaListener(id = "webclient-consumers",
            topics = Topics.SCREENSHOT_REQUEST,
            containerFactory = "kafkaJsonListenerContainerFactory"
    )
    public void consumeMessage(ScanRegisteredEvent scanRegisteredEvent)  {
        logger.info(scanRegisteredEvent.toString());
    }
}
