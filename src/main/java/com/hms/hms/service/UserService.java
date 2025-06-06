package com.hms.hms.service;

import com.hms.hms.entity.AppUser;
import com.hms.hms.payload.LoginDto;
import com.hms.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());

        if (opUser.isPresent()) {
            AppUser appUser = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())) {
                //generate token
                String token = jwtService.generateToken(appUser.getUsername());
                return token;
            } else return null;
        } else {

            return null;

        }
    }
}
