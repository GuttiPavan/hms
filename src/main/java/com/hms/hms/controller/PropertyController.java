package com.hms.hms.controller;



import com.hms.hms.entity.Property;
import com.hms.hms.payload.PropertyDto;
import com.hms.hms.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;


    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    @PostMapping

    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto,
                                                   @RequestParam Long country_id,
                                                   @RequestParam Long city_id){
        PropertyDto property = propertyService.addProperty(propertyDto, country_id, city_id);
        return new ResponseEntity<>(property, HttpStatus.CREATED);

    }

    @GetMapping("/searchHotel")
    public List<Property> searchHotels(@RequestParam String name){

        List<Property> properties= propertyService.searchHotel(name);

     return properties;
    }

   // @GetMapping("/Hotel_review")
    //public List<String> getHotelReview(@RequestParam Long propertyId){






}
