package com.hms.hms.entity;

import java.time.LocalDateTime;

public class OtpDetails {


    public OtpDetails(Integer otp, LocalDateTime expirationTime) {
        this.otp = otp;
        this.expirationTime = expirationTime;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Integer otp;
    public LocalDateTime expirationTime;

}
