package com.tunayagci.screenshot.eventregistry.event.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class ScanStartedEvent extends ScanEvent {
    private List<String> urls;

    @JsonProperty("class")
    private String className = "com.tunayagci.screenshot.eventregistry.event.scan.ScanStartedEvent";

    public ScanStartedEvent(
            @JsonProperty("scanId") String scanId,
            @JsonProperty("creationTime") Date creationTime,
            @JsonProperty("urls") List<String> urls) {
        super(scanId, creationTime);
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "ScanStartedEvent{" +
                "urls=" + urls +
                '}';
    }
}
