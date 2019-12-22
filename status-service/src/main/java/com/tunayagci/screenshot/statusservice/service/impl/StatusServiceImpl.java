package com.tunayagci.screenshot.statusservice.service.impl;

import com.tunayagci.screenshot.statusservice.dao.ScanDao;
import com.tunayagci.screenshot.statusservice.generic.ScanStatus;
import com.tunayagci.screenshot.statusservice.persistence.Scan;
import com.tunayagci.screenshot.statusservice.service.StatusService;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    private ScanDao scanDao;

    public StatusServiceImpl(ScanDao scanDao) {
        this.scanDao = scanDao;
    }

    @Override
    public ScanStatus getLatestStatus(String scanId) {
        return scanDao.findFirstByScanId(scanId).map(Scan::getScanStatus)
                .orElseThrow(() -> new NullPointerException("Could not find Scan with id: " + scanId));
    }
}
