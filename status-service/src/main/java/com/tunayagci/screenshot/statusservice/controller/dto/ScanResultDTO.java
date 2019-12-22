package com.tunayagci.screenshot.statusservice.controller.dto;

import com.tunayagci.screenshot.statusservice.generic.ScanStatus;
import com.tunayagci.screenshot.statusservice.persistence.ScanResult;

import java.util.Date;

public class ScanResultDTO {
    private Long id;
    private Date creationTime;
    private Long url;
    private String imageRef;
    private ScanStatus scanStatus;
    private String message;

    public static ScanResultDTO of(ScanResult scanResult) {
        return new ScanResultDTO(scanResult.getId(), scanResult.getCreationTime(), scanResult.getUrl(), scanResult.getImageRef(), scanResult.getScanStatus(), scanResult.getMessage());
    }

    private ScanResultDTO(Long id, Date creationTime, Long url, String imageRef, ScanStatus scanStatus, String message) {
        this.id = id;
        this.creationTime = creationTime;
        this.url = url;
        this.imageRef = imageRef;
        this.scanStatus = scanStatus;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Long getUrl() {
        return url;
    }

    public String getImageRef() {
        return imageRef;
    }

    public ScanStatus getScanStatus() {
        return scanStatus;
    }

    public String getMessage() {
        return message;
    }
}
