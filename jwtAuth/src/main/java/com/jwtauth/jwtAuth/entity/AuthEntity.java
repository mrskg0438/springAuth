package com.jwtauth.jwtAuth.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "auth")
@Data
public class AuthEntity {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String email;

    @NonNull
    private String name;

    @NonNull
    private String  password;

//    @DBRef
//    private Set<RoleEntity> roles = new HashSet<>();

    private List<String> roles;

}
