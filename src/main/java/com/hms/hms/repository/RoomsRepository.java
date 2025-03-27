package com.hms.hms.repository;

import com.hms.hms.entity.Property;
import com.hms.hms.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {


   // Rooms findByPropertyIdAndType(Long property, String type);

    @Query("SELECT r FROM Rooms r WHERE r.date BETWEEN :startDate AND :endDate and r.type=:type and r.property=:property")
    List<Rooms> findRoomsByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("type") String type,
            @Param("property") Property property
            );
}
