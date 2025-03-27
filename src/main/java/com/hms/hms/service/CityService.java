package com.hms.hms.service;

import com.hms.hms.entity.City;
import com.hms.hms.payload.CityDto;


import com.hms.hms.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private ModelMapper modelMapper;
    private CityRepository cityRepository;
    public CityService(ModelMapper modelMapper, CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    public CityDto addCity(CityDto cityDto) {

        City city=mapToEntity(cityDto);
        City savedCity = cityRepository.save(city);

        CityDto savedCityDto = mapToDto(savedCity);
        return savedCityDto;
    }




    City mapToEntity(CityDto cityDto){
        City city = modelMapper.map(cityDto, City.class);
        return city;
    }

    CityDto mapToDto(City savedCity){
        CityDto cityDto = modelMapper.map(savedCity, CityDto.class);
        return cityDto;
    }



    public void deleteCity(Long id){

        cityRepository.deleteById(id);

    }
}
