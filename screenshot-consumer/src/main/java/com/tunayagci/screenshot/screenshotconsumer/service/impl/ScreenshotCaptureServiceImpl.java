package com.tunayagci.screenshot.screenshotconsumer.service.impl;

import com.tunayagci.screenshot.screenshotconsumer.service.ScreenshotCaptureService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class ScreenshotCaptureServiceImpl implements ScreenshotCaptureService {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotCaptureServiceImpl.class);

    public byte[] getScreenshot() throws Exception {
        WebDriver webDriver = new ChromeDriver();
        BufferedImage image;
        try {
            image = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(5000))
                    .takeScreenshot(webDriver)
                    .getImage();
        } finally {
            webDriver.quit();
        }

        ByteArrayOutputStream compressed = new ByteArrayOutputStream();
        // The important part: Create in-memory stream
        ImageOutputStream outputStream = new MemoryCacheImageOutputStream(compressed);

        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();

        // Configure JPEG compression: 70% quality
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(0.7f);

        // Set your in-memory stream as the output
        jpgWriter.setOutput(outputStream);

        // Write image as JPEG w/configured settings to the in-memory stream
        // (the IIOImage is just an aggregator object, allowing you to associate
        // thumbnails and metadata to the image, it "does" nothing)
        jpgWriter.write(null, new IIOImage(image, null, null), jpgWriteParam);

        // Dispose the writer to free resources
        jpgWriter.dispose();

        return compressed.toByteArray();
    }

}
