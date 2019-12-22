package com.tunayagci.screenshot.imageservice.service.impl;

import com.tunayagci.screenshot.eventregistry.feign.ImageReference;
import com.tunayagci.screenshot.imageservice.dao.ImageDao;
import com.tunayagci.screenshot.imageservice.persistence.Image;
import com.tunayagci.screenshot.imageservice.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    @Transactional
    public ImageReference add(String scanId, String url, String base64Image) {
        final Image image = new Image();
        image.setBase64Image(base64Image);
        image.setScanId(scanId);
        image.setUrl(url);
        imageDao.save(image);
        return new ImageReference(base64Image);
    }

    @Override
    public List<ImageReference> get(String scanId) {
        return imageDao.findAllByScanId(scanId)
                .stream()
                .map(f -> new ImageReference(f.getBase64Image()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(String scanId) {
        return imageDao.existsByScanId(scanId);
    }
}
