package com.hms.hms.service;

import com.hms.hms.entity.AppUser;
import com.hms.hms.entity.OtpDetails;
import com.hms.hms.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Service

public class OtpService {

    private JWTService jwtService;
    private AppUserRepository userRepository;


    private HashMap<String, OtpDetails> map = new HashMap<>();
    private static final int OTP_EXPIRATION_MINUTES = 5; // Set OTP timeout duration

    public OtpService(JWTService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public  Integer generateOTP(String mobile) {

        Random random = new Random();
        Integer otp = random.nextInt(899999) + 100000; // Generate a 6-digit OTP
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES);

        // Store OTP and expiration time
        map.put(mobile, new OtpDetails(otp, expirationTime));

        return otp;

    }


    public String verifyOtp(String mobile, Integer otp){

        OtpDetails otpDetails = map.get(mobile);

        if (otpDetails == null) {
            return "Invalid mobile number or OTP not generated";
        }

        // Check if OTP is expired
        if (LocalDateTime.now().isAfter(otpDetails.getExpirationTime())) {
            map.remove(mobile); // Remove expired OTP
            return "OTP has expired";
        }

        // Check if OTP matches
        if (otpDetails.getOtp().equals(otp)) {
            Optional<AppUser> opUser = userRepository.findByMobile(mobile);
            if (opUser.isPresent()) {
                AppUser appUser = opUser.get();
                String token = jwtService.generateToken(appUser.getUsername());
                map.remove(mobile); // Remove OTP after successful verification
                return  token;
            }
        }
        return "Invalid OTP";

    }


}
