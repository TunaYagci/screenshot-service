package com.tunayagci.screenshot.statusservice.controller.dto;

import com.tunayagci.screenshot.statusservice.generic.ScanStatus;
import com.tunayagci.screenshot.statusservice.persistence.Scan;
import com.tunayagci.screenshot.statusservice.persistence.ScanResult;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ScanDTO {
    private Long id;
    private Date creationTime;
    private ScanStatus scanStatus;
    private List<ScanResultDTO> scanResults;

    public static ScanDTO of(Scan scan) {
        final List<ScanResult> scanResults = scan.getScanResults();
        final List<ScanResultDTO> scanResultDTOS = scanResults.stream()
                .map(ScanResultDTO::of)
                .collect(Collectors.toList());
        return new ScanDTO(scan.getId(), scan.getCreationTime(), scan.getScanStatus(), scanResultDTOS);
    }

    private ScanDTO(Long id, Date creationTime, ScanStatus scanStatus, List<ScanResultDTO> scanResults) {
        this.id = id;
        this.creationTime = creationTime;
        this.scanStatus = scanStatus;
        this.scanResults = scanResults;
    }

    public Long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public ScanStatus getScanStatus() {
        return scanStatus;
    }

    public List<ScanResultDTO> getScanResults() {
        return scanResults;
    }
}
