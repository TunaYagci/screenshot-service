package com.tunayagci.screenshot.screenshotconsumer.service;

public interface ScreenshotCaptureService {

    byte[] getScreenshot(String url) throws Exception;
}
