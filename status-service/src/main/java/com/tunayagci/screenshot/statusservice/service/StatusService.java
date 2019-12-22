package com.tunayagci.screenshot.statusservice.service;

import com.tunayagci.screenshot.statusservice.generic.ScanStatus;

public interface StatusService {

    ScanStatus getLatestStatus(String scanId);
}
