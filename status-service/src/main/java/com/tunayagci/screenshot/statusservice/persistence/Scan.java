package com.tunayagci.screenshot.statusservice.persistence;

import com.tunayagci.screenshot.statusservice.generic.ScanStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Scan extends UniqueEntity {

    @Column(name = "scan_register_id", nullable = false, updatable = false)
    private String scanRegisterId;

    @Column(name = "scan_status", nullable = false)
    private ScanStatus scanStatus;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ScanResult> scanResults = new ArrayList<>();

    @CollectionTable(name = "scan_request_urls")
    private List<String> requestUrls = new ArrayList<>();

    public Scan() {
    }

    public String getScanRegisterId() {
        return scanRegisterId;
    }

    public void setScanRegisterId(String scanRegisterId) {
        this.scanRegisterId = scanRegisterId;
    }

    public ScanStatus getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(ScanStatus scanStatus) {
        this.scanStatus = scanStatus;
    }

    public List<ScanResult> getScanResults() {
        return scanResults;
    }

    public void setScanResults(List<ScanResult> scanResults) {
        this.scanResults = scanResults;
    }

    public List<String> getRequestUrls() {
        return requestUrls;
    }

    public void setRequestUrls(List<String> requestUrls) {
        this.requestUrls = requestUrls;
    }
}
