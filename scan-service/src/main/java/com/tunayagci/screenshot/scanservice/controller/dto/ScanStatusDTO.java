package com.tunayagci.screenshot.scanservice.controller.dto;

public class ScanStatusDTO {
    private final String scanID;
    private final long creationTime;

    public ScanStatusDTO(String scanID, long creationTime) {
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
