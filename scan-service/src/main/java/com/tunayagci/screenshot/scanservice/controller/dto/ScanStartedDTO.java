package com.tunayagci.screenshot.scanservice.controller.dto;

public class ScanStartedDTO {
    private final String scanID;
    private final long creationTime;

    public ScanStartedDTO(String scanID, long creationTime) {
        this.scanID = scanID;
        this.creationTime = creationTime;
    }

    public String getScanID() {
        return scanID;
    }

    public long getCreationTime() {
        return creationTime;
    }
}
