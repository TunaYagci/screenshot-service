package com.tunayagci.screenshot.imageservice.controller;

import com.tunayagci.screenshot.imageservice.controller.dto.ImageDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageAPI {

    @PostMapping
    public ImageDTO upload(String base64Image) {
        return new ImageDTO("tuna");
    }
}
