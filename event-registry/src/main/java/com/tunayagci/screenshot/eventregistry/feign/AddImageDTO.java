package com.tunayagci.screenshot.eventregistry.feign;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddImageDTO {
    private String scanId;
    private String url;
    private byte[] image;

    public AddImageDTO(
            @JsonProperty("scanId") String scanId,
            @JsonProperty("url") String url,
            @JsonProperty("image") byte[] image) {
        this.scanId = scanId;
        this.url = url;
        this.image = image;
    }

    public String getScanId() {
        return scanId;
    }

    public String getUrl() {
        return url;
    }

    public byte[] getImage() {
        return image;
    }
}
