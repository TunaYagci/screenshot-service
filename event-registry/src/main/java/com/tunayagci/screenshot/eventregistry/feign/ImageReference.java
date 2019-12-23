package com.tunayagci.screenshot.eventregistry.feign;

public class ImageReference {
   private byte[] image;

    public ImageReference(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }
}
