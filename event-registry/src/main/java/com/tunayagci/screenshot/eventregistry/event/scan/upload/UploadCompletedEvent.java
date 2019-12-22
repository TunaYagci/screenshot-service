package com.tunayagci.screenshot.eventregistry.event.scan.upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanEvent;

import java.util.Date;

public class UploadCompletedEvent extends ScanEvent {
    private String fileName;
    private String url;

    public UploadCompletedEvent(
            @JsonProperty("scanId") String scanId,
            @JsonProperty("creationTime") Date creationTime,
            @JsonProperty("fileName") String fileName,
            @JsonProperty("url") String url) {
        super(scanId, creationTime);
        this.fileName = fileName;
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUrl() {
        return url;
    }
}
