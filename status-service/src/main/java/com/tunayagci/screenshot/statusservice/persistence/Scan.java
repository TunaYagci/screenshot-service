package com.tunayagci.screenshot.statusservice.persistence;

import com.tunayagci.screenshot.statusservice.generic.ScanStatus;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Scan extends UniqueEntity {

    private String scanId;
    private ScanStatus scanStatus;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ScanResult> scanResults = new ArrayList<>();

    public Scan() {
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
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
}
