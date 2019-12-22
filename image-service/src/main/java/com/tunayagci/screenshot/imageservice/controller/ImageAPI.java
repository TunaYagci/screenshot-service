package com.tunayagci.screenshot.imageservice.controller;

import com.tunayagci.screenshot.eventregistry.feign.AddImageDTO;
import com.tunayagci.screenshot.eventregistry.feign.ImageReference;
import com.tunayagci.screenshot.imageservice.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageAPI {

    private ImageService imageService;

    public ImageAPI(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ImageReference upload(@RequestBody AddImageDTO addImageDTO) {
        return imageService.add(addImageDTO.getScanId(), addImageDTO.getUrl(), addImageDTO.getBase64Image());
    }

    @GetMapping
    public List<ImageReference> get(String scanId) {
        return imageService.get(scanId);
    }

    @GetMapping("/exists")
    public boolean exists(String scanId) {
        return imageService.exists(scanId);
    }
}
