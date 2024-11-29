package com.jwtauth.jwtAuth.service;


import com.jwtauth.jwtAuth.entity.AuthEntity;
import com.jwtauth.jwtAuth.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AuthRepo authRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthEntity authEntity = authRepo.findByName(username);
        if(authEntity != null){
            System.out.println(authEntity);
            return User.builder().username(authEntity.getName())
                    .password(authEntity.getPassword())
                    .roles(authEntity.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User Not found with userName" + username);
    }

}
