package com.tunayagci.screenshot.scanservice.controller;

import com.tunayagci.screenshot.scanservice.controller.dto.ScanStartedDTO;
import com.tunayagci.screenshot.scanservice.service.ScanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/scan")
public class ScanAPI {

    private ScanService scanService;

    public ScanAPI(ScanService scanService) {
        this.scanService = scanService;
    }

    @PostMapping
    public ScanStartedDTO startScan(@RequestParam("urls") List<String> urls) throws ExecutionException, InterruptedException {
        return scanService.startScan(urls);
    }
}
