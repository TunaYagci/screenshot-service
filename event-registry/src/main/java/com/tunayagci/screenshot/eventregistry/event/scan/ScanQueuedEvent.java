package com.tunayagci.screenshot.eventregistry.event.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ScanQueuedEvent extends ScanEvent {

    private String url;

    @JsonProperty("class")
    private String className = "com.tunayagci.screenshot.eventregistry.event.scan.ScanQueuedEvent";

    public ScanQueuedEvent(@JsonProperty("scanId") String scanId,
                           @JsonProperty("creationTime") Date creationTime,
                           @JsonProperty("url") String url) {
        super(scanId, creationTime);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "ScanQueuedEvent{" +
                "url='" + url + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
