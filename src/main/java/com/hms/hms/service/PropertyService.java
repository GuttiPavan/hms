package com.hms.hms.service;


import com.hms.hms.entity.City;
import com.hms.hms.entity.Country;
import com.hms.hms.entity.Property;


import com.hms.hms.payload.PropertyDto;
import com.hms.hms.repository.CityRepository;
import com.hms.hms.repository.CountryRepository;
import com.hms.hms.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;
    private  CountryRepository countryRepository;
    private  CityRepository cityRepository;

    public PropertyService(ModelMapper modelMapper, PropertyRepository propertyRepository,
                           CountryRepository countryRepository,
                           CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    public PropertyDto addProperty(PropertyDto propertyDto, Long country_id, Long city_id) {


       // Property property = mapToEntity(propertyDto);
        Country country = countryRepository.findById(country_id).get();
        propertyDto.setCountry(country);

        City city = cityRepository.findById(city_id).get();
        propertyDto.setCity(city);

        Property property = mapToEntity(propertyDto);

        Property savedProperty = propertyRepository.save(property);

        PropertyDto savedpropertyDto = mapToDto(savedProperty);


        return  savedpropertyDto;



    }



    Property mapToEntity(PropertyDto propertyDto){
      Property property = modelMapper.map(propertyDto,Property.class);
        return property;
    }

    PropertyDto mapToDto(Property savedProperty){
      return modelMapper.map(savedProperty,PropertyDto.class);

    }


    public List<Property> searchHotel(String name) {

        List<Property> property = (List<Property>) propertyRepository.searchHotel(name);
        return property;
    }






}
