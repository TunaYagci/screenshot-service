package com.tunayagci.screenshot.imageservice.dao;

import com.tunayagci.screenshot.imageservice.persistence.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageDao extends JpaRepository<Image, Long> {

    List<Image> findAllByScanId(String scanId);

    boolean existsByScanId(String scanId);
}
