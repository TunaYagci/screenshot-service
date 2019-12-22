package com.tunayagci.screenshot.eventregistry.event.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ScanRegisteredEvent extends ScanEvent {

    private String url;

    public ScanRegisteredEvent(@JsonProperty("scanId") String scanId,
                               @JsonProperty("creationTime") Date creationTime,
                               @JsonProperty("url") String url) {
        super(scanId, creationTime);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ScanRegisteredEvent{" +
                "url='" + url + '\'' +
                '}';
    }
}
