package com.hms.hms.controller;

import com.hms.hms.payload.CityDto;

import com.hms.hms.service.CityService;
;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

        private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping

        public ResponseEntity<CityDto> addCity(
                @RequestBody CityDto cityDto){
            CityDto city = cityService.addCity(cityDto);
            return new ResponseEntity<>(city, HttpStatus.CREATED);

        }

     @DeleteMapping
     public ResponseEntity<String> deleteCity(
             @RequestParam Long id){
         cityService.deleteCity(id);
         return new ResponseEntity<>("Deleted",HttpStatus.OK);

     }
}
