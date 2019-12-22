package com.tunayagci.screenshot.statusservice.controller;

import com.tunayagci.screenshot.statusservice.generic.ScanStatus;
import com.tunayagci.screenshot.statusservice.service.StatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusAPI {

    private StatusService statusService;

    public StatusAPI(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    private ScanStatus scanStatus(String scanId) {
        return statusService.getLatestStatus(scanId);
    }
}
