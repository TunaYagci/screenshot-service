package com.tunayagci.screenshot.scanservice.service;

import com.tunayagci.screenshot.scanservice.controller.dto.ScanStartedDTO;
import com.tunayagci.screenshot.scanservice.producer.ScanRequestProducer;
import com.tunayagci.screenshot.scanservice.producer.ScanStartedProducer;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ScanService {

    private ScanRequestProducer scanRequestProducer;
    private ScanStartedProducer scanStartedProducer;

    public ScanService(ScanRequestProducer scanRequestProducer, ScanStartedProducer scanStartedProducer) {
        this.scanRequestProducer = scanRequestProducer;
        this.scanStartedProducer = scanStartedProducer;
    }

    public ScanStartedDTO startScan(List<String> urls) throws ExecutionException, InterruptedException {
        final String scanID = UUID.randomUUID().toString();
        scanStartedProducer.scanStartedEvent(scanID, scanID, urls);

        for (int i = 0; i < urls.size(); i++) {
            final String url = urls.get(i);
            String key = String.format("%s-%d", url, i + 1);
            scanRequestProducer.scanRequest(key, scanID, url);
        }
        return new ScanStartedDTO(scanID, new Date().getTime());
    }
}
