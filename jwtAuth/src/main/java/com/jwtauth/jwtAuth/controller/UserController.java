package com.jwtauth.jwtAuth.controller;


import com.jwtauth.jwtAuth.entity.AuthEntity;
import com.jwtauth.jwtAuth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthService authService;
    @GetMapping
    public ResponseEntity<?> getByUsername(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AuthEntity authEntity = authService.findByEmail(userName);
        System.out.println(userName+ authEntity);
        if (authEntity != null ) {
            return ResponseEntity.ok(authEntity);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
