package com.tunayagci.screenshot.statusservice.dao;

import com.tunayagci.screenshot.statusservice.persistence.Scan;
import com.tunayagci.screenshot.statusservice.persistence.ScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScanResultDao extends JpaRepository<ScanResult, Long> {

    Optional<ScanResult> findFirstByScanAndUrl(Scan scan, String url);
}
