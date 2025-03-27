package com.hms.hms.service;

import com.hms.hms.entity.Image;
import com.hms.hms.entity.Property;
import com.hms.hms.repository.ImageRepository;
import com.hms.hms.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private PropertyRepository propertyRepository;
    private final ImageRepository imageRepository;

    public ImageService(PropertyService propertyService, PropertyRepository propertyRepository,
                        ImageRepository imageRepository) {
        this.propertyRepository = propertyRepository;

        this.imageRepository = imageRepository;
    }

    public Property getHotelId(Long property_Id) {

      Property pid = propertyRepository.findById(property_Id).get();

       return pid;
    }


    public Object getPropertyImage(Long propertyId) {


       List<Image> images = imageRepository.findByPropertyId(propertyId);

        if (images == null || images.isEmpty()) {

            return "No image found for property ID: " + propertyId;


           // throw new RuntimeException("No image found for property ID: " + propertyId);
        }



        return images;



    }
}
