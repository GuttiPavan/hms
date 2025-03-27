package com.hms.hms.controller;

import com.hms.hms.entity.Image;
import com.hms.hms.entity.Property;
import com.hms.hms.repository.ImageRepository;
import com.hms.hms.service.ImageService;
import com.hms.hms.service.PropertyService;
import com.hms.hms.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {


    private S3Service s3Service;
    private ImageService imageService;
    private ImageRepository imageRepository;

    public ImageController(S3Service s3Service, ImageRepository imageRepository, PropertyService propertyService, ImageService imageService, ImageRepository imageRepository1) {
        this.s3Service = s3Service;
        this.imageService = imageService;

        this.imageRepository = imageRepository1;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file,@RequestParam  Long propertyId) {
        try {
            String imageUrl = s3Service.uploadFileToS3(file);

          Property pid  =imageService.getHotelId(propertyId);

            // Store the image URL in MySQL
            Image image = new Image();
            image.setImageUrl(imageUrl);
            image.setProperty(pid);

            imageRepository.save(image);


            return ResponseEntity.ok("Image uploaded successfully. URL: " + imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading image: " + e.getMessage());
        }
    }



    @GetMapping("/property-image")
    public ResponseEntity<Object> getPropertyImage(@RequestParam Long propertyId) {
       Object image = imageService.getPropertyImage(propertyId);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
