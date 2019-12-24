package com.tunayagci.screenshot.scanservice.service;

import com.tunayagci.screenshot.scanservice.controller.dto.ScanStartedDTO;
import com.tunayagci.screenshot.scanservice.producer.ScanRequestProducer;
import com.tunayagci.screenshot.scanservice.producer.ScanStatusProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ScanService {
    private static final Logger logger = LoggerFactory.getLogger(ScanService.class);

    private ScanRequestProducer scanRequestProducer;
    private ScanStatusProducer scanStatusProducer;

    public ScanService(ScanRequestProducer scanRequestProducer, ScanStatusProducer scanStatusProducer) {
        this.scanRequestProducer = scanRequestProducer;
        this.scanStatusProducer = scanStatusProducer;
    }

    public ScanStartedDTO startScan(List<String> urls) throws ExecutionException, InterruptedException {
        final String scanID = UUID.randomUUID().toString();
        scanStatusProducer.scanStartedEvent(scanID, scanID, urls);

        for (int i = 0; i < urls.size(); i++) {
            final String url = urls.get(i);
            String key = String.format("%s-%d", url, i + 1);
            try {
                scanRequestProducer.scanRequest(key, scanID, url);
            } catch (Exception e) {
                logger.error("Error sending message", e);
                scanStatusProducer.scanFailedEvent(key, scanID, url, e.getMessage());
            }
        }
        return new ScanStartedDTO(scanID, new Date().getTime());
    }
}
