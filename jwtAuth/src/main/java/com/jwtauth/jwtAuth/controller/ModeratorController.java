package com.jwtauth.jwtAuth.controller;


import com.jwtauth.jwtAuth.entity.AuthEntity;
import com.jwtauth.jwtAuth.service.AuthService;
import com.jwtauth.jwtAuth.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mod")
public class ModeratorController {
    @Autowired
    private AuthService authService;

    @Autowired
    UserDetailServiceImpl userDetailService;


    @GetMapping
    public ResponseEntity<?> getList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        List<AuthEntity> lists = new ArrayList<>();
        if (userDetails != null && userDetails.getAuthorities() != null && userDetails.getAuthorities().toString().contains("ROLE_ADMIN")) {
            lists = authService.getAllEntries();

        }
        else {
            for (AuthEntity authEntity : authService.getAllEntries()) {
                if (authEntity.getRoles().toString().contains("MODERATOR") || authEntity.getRoles().toString().contains("USER")) {
                    lists.add(authEntity);
                }
            }
        }
        if (!lists.isEmpty()) {
            return ResponseEntity.ok(lists);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
