package com.tunayagci.screenshot.eventregistry.event.scan.upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanEvent;

import java.util.Date;

public class UploadImageRequestEvent extends ScanEvent {
    private String base64Image;
    private String url;

    public UploadImageRequestEvent(
            @JsonProperty("scanId") String scanId,
            @JsonProperty("creationTime") Date creationTime,
            @JsonProperty("base64Image") String base64Image,
            @JsonProperty("url") String url) {
        super(scanId, creationTime);
        this.base64Image = base64Image;
        this.url = url;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "UploadImageRequestEvent{" +
                "base64Image='" + base64Image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
