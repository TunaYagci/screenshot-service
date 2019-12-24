package com.tunayagci.screenshot.statusservice.service;

import com.tunayagci.screenshot.eventregistry.event.scan.ScanFailedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanQueuedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.ScanStartedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotCompletedEvent;
import com.tunayagci.screenshot.eventregistry.event.scan.screenshot.ScreenshotFailedEvent;
import com.tunayagci.screenshot.statusservice.controller.dto.ScanDTO;
import com.tunayagci.screenshot.statusservice.generic.ScanStatus;

public interface StatusService {

    ScanStatus getLatestStatus(String scanId);

    void scanStarted(ScanStartedEvent scanStartedEvent);

    void scanFailed(ScanFailedEvent scanEvent);

    void scanQueued(ScanQueuedEvent scanEvent);

    void screenshotCompleted(ScreenshotCompletedEvent scanEvent);

    void screenshotFailed(ScreenshotFailedEvent scanEvent);

    ScanDTO getScanResult(String scanId);
}
