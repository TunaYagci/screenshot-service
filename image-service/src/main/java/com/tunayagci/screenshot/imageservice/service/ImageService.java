package com.tunayagci.screenshot.imageservice.service;

import com.tunayagci.screenshot.imageservice.controller.dto.ImageReference;

import java.util.List;

public interface ImageService {

    boolean add(String scanId, String url, byte[] image);

    List<ImageReference> findAllByScanId(String scanId);

    byte[] find(String scanId, Long id);

    boolean exists(String scanId);
}
