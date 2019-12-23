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
    public ImageReference add(String scanId, String url, byte[] image) {
        final Image imageEntity = new Image();
        imageEntity.setImageBytes(image);
        imageEntity.setScanId(scanId);
        imageEntity.setUrl(url);
        imageDao.save(imageEntity);
        return new ImageReference(image);
    }

    @Override
    @Transactional
    public List<ImageReference> get(String scanId) {
        return imageDao.findAllByScanId(scanId)
                .stream()
                .map(f -> new ImageReference(f.getImageBytes()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(String scanId) {
        return imageDao.existsByScanId(scanId);
    }
}
