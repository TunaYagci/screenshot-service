package com.tunayagci.screenshot.imageservice.service.impl;

import com.tunayagci.screenshot.imageservice.controller.dto.ImageReference;
import com.tunayagci.screenshot.imageservice.dao.ImageDao;
import com.tunayagci.screenshot.imageservice.persistence.Image;
import com.tunayagci.screenshot.imageservice.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${application.dns}")
    private String applicationDNS;

    private ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    @Transactional
    public String add(String scanId, String url, byte[] image) {
        final Image imageEntity = new Image();
        imageEntity.setImageBytes(image);
        imageEntity.setScanId(scanId);
        imageEntity.setUrl(url);
        final Image savedImage = imageDao.save(imageEntity);
        return String.format("%s/image/%s/%d", applicationName, scanId, savedImage.getId());
    }

    @Override
    @Transactional
    public List<ImageReference> findAllByScanId(String scanId) {
        return imageDao.findAllByScanId(scanId)
                .stream()
                .map(ImageReference::of)
                .peek(f -> f.setImageURL(constructImageUrl(f.getId(), f.getScanId())))
                .collect(Collectors.toList());
    }

    @Override
    public byte[] find(String scanId, Long id) {
        return imageDao.findById(id)
                .map(Image::getImageBytes)
                .orElseThrow(() -> new NullPointerException("Cannot find image"));
    }

    private String constructImageUrl(Long id, String scanId) {
        return String.format("%s/%s/image/%s/%d", applicationDNS, applicationName, scanId, id);
    }

    @Override
    public boolean exists(String scanId) {
        return imageDao.existsByScanId(scanId);
    }
}
