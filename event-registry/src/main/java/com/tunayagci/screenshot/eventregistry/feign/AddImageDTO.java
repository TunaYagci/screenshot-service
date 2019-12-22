package com.tunayagci.screenshot.eventregistry.feign;

public class AddImageDTO {
    private String scanId;
    private String url;
    private String base64Image;

    public AddImageDTO(String scanId, String url, String base64Image) {
        this.scanId = scanId;
        this.url = url;
        this.base64Image = base64Image;
    }

    public String getScanId() {
        return scanId;
    }

    public String getUrl() {
        return url;
    }

    public String getBase64Image() {
        return base64Image;
    }
}
