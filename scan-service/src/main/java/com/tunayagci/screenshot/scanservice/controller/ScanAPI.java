package com.tunayagci.screenshot.scanservice.controller;

import com.tunayagci.screenshot.scanservice.controller.dto.ScanStartedDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/scan")
public class ScanAPI {

    @PostMapping
    public ScanStartedDTO startScan() {
        final String scanID = UUID.randomUUID().toString();
        return new ScanStartedDTO(scanID, new Date().getTime());
    }
}
