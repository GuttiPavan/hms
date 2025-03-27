package com.hms.hms.payload;

import com.hms.hms.entity.City;
import com.hms.hms.entity.Country;
import lombok.Data;

@Data
public class PropertyDto {
    private Long id;
    private String name;
    private Integer no_of_guest;
    private Integer no_of_bathrooms;
    private String no_of_rooms;
    private Integer no_of_beds;

    private Country country;
    private City city;
}
