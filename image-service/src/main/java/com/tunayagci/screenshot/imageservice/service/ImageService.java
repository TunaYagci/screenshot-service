package com.tunayagci.screenshot.imageservice.service;

import com.tunayagci.screenshot.eventregistry.feign.ImageReference;

import java.util.List;

public interface ImageService {

    ImageReference add(String scanId, String url, byte[] base64Image);

    List<ImageReference> get(String scanId);

    boolean exists(String scanId);
}
