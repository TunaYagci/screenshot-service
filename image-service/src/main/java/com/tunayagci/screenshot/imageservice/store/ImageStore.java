package com.tunayagci.screenshot.imageservice.store;

import com.tunayagci.screenshot.imageservice.generic.ImageReference;

import java.util.List;

public interface ImageStore {

    ImageReference add(String base64Image);

    List<ImageReference> get(Long scanId);

    boolean exists();

    void delete();
}
