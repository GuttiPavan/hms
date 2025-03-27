package com.hms.hms.repository;

import com.hms.hms.entity.AppUser;
import com.hms.hms.entity.Property;
import com.hms.hms.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findByAppUser(AppUser user);

    boolean existsByAppUserAndProperty(AppUser user,Property property);
}