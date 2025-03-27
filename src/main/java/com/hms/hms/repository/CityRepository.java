package com.hms.hms.repository;

import com.hms.hms.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
   //void deleteByName(String name);
}