package com.hms.hms.config;

import com.hms.hms.entity.AppUser;
import com.hms.hms.repository.AppUserRepository;
import com.hms.hms.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private  JWTService jwtService;
    private AppUserRepository userRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String token = request.getHeader("Authorization");
        System.out.println(token);

if(token!=null&&token.startsWith("Bearer ")){
    String tokenval = token.substring(8, token.length()-1);
    String username=jwtService.getUsername(tokenval);


    //checking user name get  by token exist in db or not
    System.out.println(username);
    Optional<AppUser> opUsername = userRepository.findByUsername(username);

   if(opUsername.isPresent()){

       AppUser appUser = opUsername.get();
       UsernamePasswordAuthenticationToken  authenticationToken=new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton( new SimpleGrantedAuthority(appUser.getRole())));

       authenticationToken.setDetails(new WebAuthenticationDetails(request));

       SecurityContextHolder.getContext().setAuthentication(authenticationToken);

   }


}
//Finally, filterChain.doFilter(request, response) is called to pass the request
// along to the next filter in the chain or to the intended target (like a controller).
        //ex:login/sign up
       filterChain.doFilter(request,response);

    }




}
