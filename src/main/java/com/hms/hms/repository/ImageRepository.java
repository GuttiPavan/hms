package com.hms.hms.repository;

import com.hms.hms.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPropertyId(Long propertyId);
}