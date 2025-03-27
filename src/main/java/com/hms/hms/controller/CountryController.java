package com.hms.hms.controller;


import com.hms.hms.entity.Country;
import com.hms.hms.payload.CountryDto;
import com.hms.hms.repository.CountryRepository;
import com.hms.hms.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping

    public ResponseEntity<CountryDto> addCountry(
            @RequestBody  CountryDto countryDto){
        CountryDto country = countryService.addCountry(countryDto);
        return new ResponseEntity<CountryDto>(country,HttpStatus.CREATED);

    }
}
