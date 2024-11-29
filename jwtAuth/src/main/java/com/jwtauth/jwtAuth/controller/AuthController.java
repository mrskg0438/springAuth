package com.jwtauth.jwtAuth.controller;


import com.jwtauth.jwtAuth.entity.AuthEntity;
import com.jwtauth.jwtAuth.response.MessageResponse;
import com.jwtauth.jwtAuth.service.AuthService;
import com.jwtauth.jwtAuth.service.UserDetailServiceImpl;
import com.jwtauth.jwtAuth.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailsService;
    
    @Autowired
    JWTUtils jwtUtils;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AuthEntity signupRequest){
        if (authService.existsByUsername(signupRequest.getName())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));

        }
        if(authService.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Ensure roles are properly set if your system requires them
        if (signupRequest.getRoles() == null || signupRequest.getRoles().isEmpty()) {
            List<String> defaultRoles = new ArrayList<>();
            defaultRoles.add("USER"); // Assign a default role
            signupRequest.setRoles(defaultRoles);
        }

        authService.saveEntry(signupRequest);
        return ResponseEntity.ok().body(new MessageResponse("Success!"));

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthEntity signupRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signupRequest.getName(), signupRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(signupRequest.getName());
            String jwt  = jwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Exception occured", e);
            return new ResponseEntity<>("Incorrect Username or Password", HttpStatus.BAD_REQUEST);
        }

    }




}
