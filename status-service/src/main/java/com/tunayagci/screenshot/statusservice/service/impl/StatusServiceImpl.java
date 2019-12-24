package com.tunayagci.screenshot.statusservice.service.impl;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanFailedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanQueuedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanStartedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotCompletedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotFailedEvent;
import com.tunayagci.screenshot.statusservice.dao.ScanDao;
import com.tunayagci.screenshot.statusservice.dao.ScanResultDao;
import com.tunayagci.screenshot.statusservice.generic.ScanStatus;
import com.tunayagci.screenshot.statusservice.persistence.Scan;
import com.tunayagci.screenshot.statusservice.persistence.ScanResult;
import com.tunayagci.screenshot.statusservice.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class StatusServiceImpl implements StatusService {

    private ScanDao scanDao;
    private ScanResultDao scanResultDao;

    public StatusServiceImpl(ScanDao scanDao, ScanResultDao scanResultDao) {
        this.scanDao = scanDao;
        this.scanResultDao = scanResultDao;
    }

    @Override
    public ScanStatus getLatestStatus(String scanId) {
        return scanDao.findFirstByScanId(scanId).map(Scan::getScanStatus)
                .orElseThrow(() -> new NullPointerException("Could not find Scan with id: " + scanId));
    }

    @Override
    public void scanStarted(ScanStartedEvent scanStartedEvent) {
        final Scan scan = new Scan();
        scan.setScanStatus(ScanStatus.STARTED);
        scan.setScanRegisterId(scanStartedEvent.getScanId());
        scan.setRequestUrls(scanStartedEvent.getUrls());
        scanDao.save(scan);
    }

    @Override
    public void scanFailed(ScanFailedEvent scanEvent) {
        final Scan scan = scanDao.findFirstByScanId(scanEvent.getScanId())
                .orElseThrow(() -> new NullPointerException("Cannot find scan with id " + scanEvent.getScanId()));
        final ScanResult scanResult = new ScanResult();
        scanResult.setMessage(scanEvent.getErrorMessage());
        scanResult.setScanStatus(ScanStatus.FAILED);
        scanResult.setUrl(scanEvent.getUrl());
        scanResult.setScan(scan);
        scanResultDao.save(scanResult);

        checkIfScanIsFinished(scan);
    }

    @Override
    public void scanQueued(ScanQueuedEvent scanEvent) {
        final Supplier<NullPointerException> scanNotFoundException = () -> new NullPointerException("Cannot find scan with id " + scanEvent.getScanId());
        final Scan scan = scanDao.findFirstByScanId(scanEvent.getScanId())
                .orElseThrow(scanNotFoundException);
        final ScanResult scanResult = scanResultDao.findFirstByScanAndUrl(scan, scanEvent.getUrl())
                .orElseThrow(scanNotFoundException);
        scanResult.setScanStatus(ScanStatus.QUEUED);
        scanResultDao.save(scanResult);
    }

    @Override
    public void screenshotCompleted(ScreenshotCompletedEvent scanEvent) {
        final Supplier<NullPointerException> scanNotFoundException = () -> new NullPointerException("Cannot find scan with id " + scanEvent.getScanId());
        final Scan scan = scanDao.findFirstByScanId(scanEvent.getScanId())
                .orElseThrow(scanNotFoundException);
        final ScanResult scanResult = scanResultDao.findFirstByScanAndUrl(scan, scanEvent.getUrl())
                .orElseThrow(scanNotFoundException);
        scanResult.setScanStatus(ScanStatus.COMPLETED);
        scanResult.setImageRef(scanEvent.getImageURL());
        checkIfScanIsFinished(scan);
    }

    @Override
    public void screenshotFailed(ScreenshotFailedEvent scanEvent) {
        final Supplier<NullPointerException> scanNotFoundException = () -> new NullPointerException("Cannot find scan with id " + scanEvent.getScanId());
        final Scan scan = scanDao.findFirstByScanId(scanEvent.getScanId())
                .orElseThrow(scanNotFoundException);
        final ScanResult scanResult = scanResultDao.findFirstByScanAndUrl(scan, scanEvent.getUrl())
                .orElseThrow(scanNotFoundException);
        scanResult.setScanStatus(ScanStatus.FAILED);
        checkIfScanIsFinished(scan);
    }


    private void checkIfScanIsFinished(Scan scan) {
        if (scan.getScanResults().size() == scan.getRequestUrls().size()) {
            scan.setScanStatus(ScanStatus.COMPLETED);
            scanDao.save(scan);
        } else {
            scan.setScanStatus(ScanStatus.PARTIAL_COMPLETED);
            scanDao.save(scan);
        }
    }
}
