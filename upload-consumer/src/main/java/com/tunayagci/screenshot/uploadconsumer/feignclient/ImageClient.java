package com.tunayagci.screenshot.uploadconsumer.feignclient;

import com.tunayagci.screenshot.eventregistry.feign.AddImageDTO;
import com.tunayagci.screenshot.eventregistry.feign.ImageReference;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("zuul-api-gateway")
public interface ImageClient {

    @PostMapping("/image-service/image/")
    ImageReference uploadImage(AddImageDTO addImageDTO);
}
