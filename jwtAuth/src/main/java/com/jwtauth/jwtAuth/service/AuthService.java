package com.jwtauth.jwtAuth.service;


import com.jwtauth.jwtAuth.entity.AuthEntity;
import com.jwtauth.jwtAuth.repository.AuthRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthService {
    @Autowired
    private AuthRepo authRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(AuthEntity authEntity){
        authEntity.setPassword(passwordEncoder.encode(authEntity.getPassword()));
        authRepo.save(authEntity);
    }

    public List<AuthEntity> getAllEntries(){
       return authRepo.findAll();
    }

    public AuthEntity findByName(String name){
        return authRepo.findByName(name);
    }
    public Boolean existsByUsername(String name){
        return authRepo.existsByName(name);
    }
    public Boolean existsByEmail(String email){
        return authRepo.existsByEmail(email);
    }
    public AuthEntity findByEmail(String email){
        return authRepo.findByEmail(email);
    }
    public void deleteById(ObjectId id){
        authRepo.deleteById(id);
    }
//    public String getRole(String role){
//        authRepo.
//    }
}
