package com.tunayagci.screenshot.screenshotconsumer.feignclient;

import com.tunayagci.screenshot.eventregistry.feign.AddImageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("zuul-api-gateway")
public interface ImageClient {

    @PostMapping("/image-service/image/")
    String uploadImage(AddImageDTO addImageDTO);
}
