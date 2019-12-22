package com.tunayagci.screenshot.statusservice.dao;

import com.tunayagci.screenshot.statusservice.persistence.ScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanResultDao extends JpaRepository<ScanResult, Long> {
}
