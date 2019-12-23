package com.tunayagci.screenshot.imageservice.controller.dto;

import com.tunayagci.screenshot.imageservice.persistence.Image;

import java.util.Date;

public class ImageReference {
    private Long id;
    private String scanId;
    private String url;
    private Date creationTime;
    private String imageURL;

    public static ImageReference of(Image image) {
        return new ImageReference(
                image.getId(),
                image.getScanId(),
                image.getUrl(),
                image.getCreationTime()
        );
    }

    private ImageReference(Long id, String scanId, String url, Date creationTime) {
        this.id = id;
        this.scanId = scanId;
        this.url = url;
        this.creationTime = creationTime;
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
    }

    public String getScanId() {
        return scanId;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
