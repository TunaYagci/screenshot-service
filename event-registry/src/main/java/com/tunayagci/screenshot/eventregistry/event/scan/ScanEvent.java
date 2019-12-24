package com.tunayagci.screenshot.eventregistry.event.scan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="class")
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
