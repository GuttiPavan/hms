package com.hms.hms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    )throws Exception
    {

        //h(cd)2
      http.csrf().disable().cors().disable();



          //haap
    // http.authorizeHttpRequests().anyRequest().permitAll();


    http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

     http.authorizeHttpRequests().requestMatchers("/api/v1/users/login","/api/v1/users/signup","/api/v1/users/signup-property-owner","/api/v1/property/searchHotel","/api/v1/bookings/create-booking").permitAll()
             .requestMatchers("/api/v1/country","/api/v1/property","/api/v1/city").hasAnyRole("OWNER","ADMIN")
             .requestMatchers("/api/v1/service/userDetails","/api/v1/service/write-review")
             .hasRole("USER")
              .anyRequest().authenticated();


      return   http.build();

    }

}
