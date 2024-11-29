package com.jwtauth.jwtAuth.repository;

import com.jwtauth.jwtAuth.entity.AuthEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface AuthRepo extends MongoRepository<AuthEntity, ObjectId> {
    AuthEntity findByName(String name);
    Boolean existsByName(String username);
    Boolean existsByEmail(String email);
    AuthEntity findByEmail(String email);
}
