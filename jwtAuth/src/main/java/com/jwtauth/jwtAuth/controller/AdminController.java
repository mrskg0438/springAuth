package com.jwtauth.jwtAuth.controller;



import com.jwtauth.jwtAuth.entity.AuthEntity;
import com.jwtauth.jwtAuth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AuthService authService;

    @GetMapping("/userList")
    public ResponseEntity<List<AuthEntity>> getAll(){
        try{

        List<AuthEntity> allData = authService.getAllEntries();
        if (!allData.isEmpty()) {
            return new ResponseEntity<>(allData, HttpStatus.OK);
        }
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

