package com.tunayagci.screenshot.screenshotconsumer.service.impl;

import com.tunayagci.screenshot.screenshotconsumer.service.ScreenshotCaptureService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ScreenshotCaptureServiceImpl implements ScreenshotCaptureService {

    @Value("${chrome.driver.url}")
    private String chromeDriver;

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotCaptureServiceImpl.class);

    public byte[] getScreenshot(String url) throws Exception {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
        WebDriver webDriver = new RemoteWebDriver(new URL(chromeDriver), chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        webDriver.navigate().to(new URL(url));
        BufferedImage image;
        try {
            image = new AShot().shootingStrategy(ShootingStrategies.simple())
                    .takeScreenshot(webDriver)
                    .getImage();
        } finally {
            webDriver.quit();
        }

        byte[] bytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            bytes = baos.toByteArray();
        }
        return bytes;
    }

}
