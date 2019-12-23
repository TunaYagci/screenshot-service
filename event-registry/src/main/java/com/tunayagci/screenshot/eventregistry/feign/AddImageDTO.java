package com.tunayagci.screenshot.eventregistry.feign;

public class AddImageDTO {
    private String scanId;
    private String url;
    private byte[] image;

    public AddImageDTO(String scanId, String url, byte[] image) {
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
