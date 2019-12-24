package com.tunayagci.screenshot.imageservice.controller;

import com.tunayagci.screenshot.eventregistry.feign.AddImageDTO;
import com.tunayagci.screenshot.imageservice.controller.dto.ImageReference;
import com.tunayagci.screenshot.imageservice.service.ImageService;
import org.springframework.http.MediaType;
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
    public String upload(@RequestBody AddImageDTO addImageDTO) {
        return imageService.add(addImageDTO.getScanId(), addImageDTO.getUrl(), addImageDTO.getImage());
    }

    @GetMapping
    public List<ImageReference> get(String scanId) {
        return imageService.findAllByScanId(scanId);
    }

    @GetMapping("/exists")
    public boolean exists(String scanId) {
        return imageService.exists(scanId);
    }

    @GetMapping(value = "/{scanId}/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable("scanId") String scanId, @PathVariable("imageId") Long imageId) {
        return imageService.find(scanId, imageId);
    }
}
