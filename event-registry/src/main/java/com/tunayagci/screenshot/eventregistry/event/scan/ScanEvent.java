package com.tunayagci.screenshot.eventregistry.event.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public abstract class ScanEvent {
    @JsonProperty("scanId")
    private String scanId;
    @JsonProperty("creationTime")
    private Date creationTime;

    public ScanEvent(String scanId, Date creationTime) {
        this.scanId = scanId;
        this.creationTime = creationTime;
    }

    public String getScanId() {
        return scanId;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
