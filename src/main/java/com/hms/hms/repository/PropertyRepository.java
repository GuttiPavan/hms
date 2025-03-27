package com.hms.hms.repository;

import com.hms.hms.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {


  //search hotels in city
  //  @Query("select p from Property p JOIN p.city c where c.name=:name")

  //search hotels in city or country
  @Query("select p from Property p JOIN p.city c JOIN p.country co where c.name=:name or co.name=:name")
    List<Property>  searchHotel(@Param("name") String name);
}