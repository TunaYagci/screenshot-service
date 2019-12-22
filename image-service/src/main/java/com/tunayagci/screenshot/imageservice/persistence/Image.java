package com.tunayagci.screenshot.imageservice.persistence;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Image {
    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "creation_time")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "image", nullable = false, updatable = false, columnDefinition = "TEXT")
    private String base64Image;

    @Column(nullable = false)
    private String scanId;

    @Column(nullable = false)
    private String url;

    public Image() {
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
