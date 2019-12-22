package com.tunayagci.screenshot.imageservice.store.impl;

import com.tunayagci.screenshot.imageservice.generic.ImageReference;
import com.tunayagci.screenshot.imageservice.store.ImageStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageStoreImpl implements ImageStore {

    @Override
    public ImageReference add(String base64Image) {
        return null;
    }

    @Override
    public List<ImageReference> get(Long scanId) {
        return null;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public void delete() {
        throw new RuntimeException("Not yet implemented");
    }
}
