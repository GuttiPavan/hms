package com.hms.hms.repository;

import com.hms.hms.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

   Optional<AppUser> findByUsername(String username);
   Optional<AppUser> findByEmail(String Email);
   Optional<AppUser> findByMobile(String mobile);
}