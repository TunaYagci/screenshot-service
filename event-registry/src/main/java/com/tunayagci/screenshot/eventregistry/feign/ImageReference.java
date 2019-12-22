package com.tunayagci.screenshot.eventregistry.feign;

public class ImageReference {
    private String base64Image;

    public ImageReference(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getBase64Image() {
        return base64Image;
    }
}
