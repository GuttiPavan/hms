package com.hms.hms.service;

import com.hms.hms.entity.Country;
import com.hms.hms.payload.CountryDto;
import com.hms.hms.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private ModelMapper modelMapper;


    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }
    public CountryDto addCountry(CountryDto countryDto) {

      Country country =mapToEntity(countryDto);
      Country savedCountry = countryRepository.save(country);

        CountryDto savedcountryDto = mapToDto(savedCountry);
        return  savedcountryDto;
    }

    Country mapToEntity(CountryDto countryDto){
        Country country = modelMapper.map(countryDto, Country.class);
        return country;
    }

    CountryDto mapToDto(Country savedCountry){
        CountryDto countryDto = modelMapper.map(savedCountry, CountryDto.class);
        return countryDto;
    }

}
