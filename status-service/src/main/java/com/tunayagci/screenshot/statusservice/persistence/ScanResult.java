package com.tunayagci.screenshot.statusservice.persistence;

import com.tunayagci.screenshot.statusservice.generic.ScanStatus;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ScanResult extends UniqueEntity {
    private Long url;
    private String imageRef;
    private ScanStatus scanStatus;
    private String message;

    @ManyToOne
    private Scan scan;

    public ScanResult() {
    }

    public Scan getScan() {
        return scan;
    }

    public Long getUrl() {
        return url;
    }

    public void setUrl(Long url) {
        this.url = url;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    public ScanStatus getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(ScanStatus scanStatus) {
        this.scanStatus = scanStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
