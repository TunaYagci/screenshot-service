package com.tunayagci.screenshot.eventregistry.event.scan.screenshot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanEvent;

import java.util.Date;

public class ScreenshotFailedEvent extends ScanEvent {
    private String errorMessage;
    private String url;

    @JsonProperty("class")
    private String className = "com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotFailedEvent";

    public ScreenshotFailedEvent(
            @JsonProperty("scanId") String scanId,
            @JsonProperty("creationTime") Date creationTime,
            @JsonProperty("errorMessage") String errorMessage,
            @JsonProperty("url") String url) {
        super(scanId, creationTime);
        this.errorMessage = errorMessage;
        this.url = url;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "ScreenshotFailedEvent{" +
                "errorMessage='" + errorMessage + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
