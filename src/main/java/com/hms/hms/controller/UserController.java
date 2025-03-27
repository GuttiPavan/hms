package com.hms.hms.controller;

import com.hms.hms.entity.AppUser;
import com.hms.hms.payload.LoginDto;
import com.hms.hms.payload.TokenDto;
import com.hms.hms.repository.AppUserRepository;
import com.hms.hms.service.JWTService;
import com.hms.hms.service.OtpService;
import com.hms.hms.service.SmsService;
import com.hms.hms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private AppUserRepository appUserRepository;
    private UserService userService;
    private SmsService smsService;
    private JWTService jwtService;
    private OtpService otpService;

    public UserController(AppUserRepository appUserRepository, UserService userService, SmsService smsService, JWTService jwtService, OtpService otpService) {
        this.appUserRepository = appUserRepository;
        this.userService = userService;
        this.smsService = smsService;
        this.jwtService = jwtService;
        this.otpService = otpService;
    }
@PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody AppUser user){
        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());

if(opUsername.isPresent()){
    return new ResponseEntity<>("user already exist", HttpStatus.INTERNAL_SERVER_ERROR);
}

if(opEmail.isPresent()){
            return new ResponseEntity<>("EMAIL already Taken", HttpStatus.INTERNAL_SERVER_ERROR);
}

    String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
    user.setPassword(encryptedPassword);
    user.setRole("ROLE_USER");

    AppUser savedUser = appUserRepository.save(user);

        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
@GetMapping ("/message")
public String getMessage(){
        return "hellow";
}


@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

   String token = userService.verifyLogin(loginDto);
if(token!=null){
    TokenDto tokenDto=new TokenDto();
    tokenDto.setToken(token);
    tokenDto.setType("JWT");


   return new ResponseEntity<>(tokenDto,HttpStatus.OK);
}else {


    return new ResponseEntity<>("Invalid user/password",HttpStatus.FORBIDDEN);
}


}

    @PostMapping("/signup-property-owner")
    public ResponseEntity<?> createPropertyOwnerUser(
            @RequestBody AppUser user){
        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());

        if(opUsername.isPresent()){
            return new ResponseEntity<>("user already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(opEmail.isPresent()){
            return new ResponseEntity<>("EMAIL already Taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(encryptedPassword);
        user.setRole("ROLE_OWNER");

        AppUser savedUser = appUserRepository.save(user);

        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }



    @GetMapping("/generateOtp/{mobile}")
    public ResponseEntity<String> generateOTP(@PathVariable String mobile) {

        Integer otp = otpService.generateOTP(mobile);

        smsService.sendSms(mobile, "Your OTP is " + otp.toString());
        return new ResponseEntity<>("OTP generated successfully :"+otp, HttpStatus.OK);
    }


  @GetMapping("/{mobile}/{otp}")
    public String verifyOtp(@PathVariable String mobile, @PathVariable Integer otp) {

      String token = otpService.verifyOtp(mobile, otp);
      return token;

  }






}
