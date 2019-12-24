package com.tunayagci.screenshot.eventregistry.event.scan.screenshot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanEvent;

import java.util.Date;

public class ScreenshotCompletedEvent extends ScanEvent {
    private String url;
    private String imageURL;

    @JsonProperty("class")
    private String className = "com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotCompletedEvent";

    public ScreenshotCompletedEvent(
            @JsonProperty("scanId") String scanId,
            @JsonProperty("creationTime") Date creationTime,
            @JsonProperty("url") String url,
            @JsonProperty("imageURL") String imageURL) {
        super(scanId, creationTime);
        this.url = url;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ScreenshotCompletedEvent{" +
                "url='" + url + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    public String getClassName() {
        return className;
    }
}
