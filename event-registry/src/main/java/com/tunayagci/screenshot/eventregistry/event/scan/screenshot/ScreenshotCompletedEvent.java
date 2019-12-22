package com.tunayagci.screenshot.eventregistry.event.scan.screenshot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanEvent;

import java.util.Date;

public class ScreenshotCompletedEvent extends ScanEvent {
    private String url;

    public ScreenshotCompletedEvent(
            @JsonProperty("scanId") String scanId,
            @JsonProperty("creationTime") Date creationTime,
            @JsonProperty("url") String url) {
        super(scanId, creationTime);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
