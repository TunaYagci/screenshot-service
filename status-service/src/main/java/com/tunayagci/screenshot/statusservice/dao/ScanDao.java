package com.tunayagci.screenshot.statusservice.dao;

import com.tunayagci.screenshot.statusservice.persistence.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanDao extends JpaRepository<Scan, Long> {
}
