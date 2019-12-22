package com.tunayagci.screenshot.imageservice.controller.dto;

public class ImageDTO {
    private String imageRef;

    public ImageDTO(String imageRef) {
        this.imageRef = imageRef;
    }

    public String getImageRef() {
        return imageRef;
    }
}
