package com.tunayagci.screenshot.eventregistry.event.scan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ScanFailedEvent extends ScanEvent {
    private String url;
    private String errorMessage;

    public ScanFailedEvent(
            @JsonProperty("scanId") String scanId,
            @JsonProperty("creationTime") Date creationTime,
            @JsonProperty("url") String url,
            @JsonProperty("errorMessage") String errorMessage) {
        super(scanId, creationTime);
        this.url = url;
        this.errorMessage = errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ScanFailedEvent{" +
                "url='" + url + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
