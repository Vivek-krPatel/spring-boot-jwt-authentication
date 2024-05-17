/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vivek.jwt_authentication_example.Service.impl;

import com.vivek.jwt_authentication_example.Entity.UserInfo;
import com.vivek.jwt_authentication_example.Exception.UserAlreadyExistsException;
import com.vivek.jwt_authentication_example.Exception.UserNotFoundException;
import com.vivek.jwt_authentication_example.Repository.UserRepository;
import com.vivek.jwt_authentication_example.Request.UserRegistrationRequest;
import com.vivek.jwt_authentication_example.Service.UserService;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vivek
 */
@Service
public class UserServiceImple implements UserService{
    private final UserRepository repo;
   // private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImple(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserInfo findByUserName(String username) {
        Optional<UserInfo> user = this.repo.findByUserName(username);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new UserNotFoundException("User not Found with " + username);
        }
    }
        

    @Override
    public boolean UserExist(String username) {
        return this.repo.existsByUserName(username);
    }
    

    @Override
    public void createUser(UserRegistrationRequest request) {
       boolean userExists = this.repo.existsByUserName(request.getUserName());
       if(! userExists) {
           UserInfo newUser = UserInfo.builder()
                                   .firstName(request.getFirstName())
                                   .lastName(request.getLastName())
                                   .email(request.getEmail())
                                   .password(passwordEncoder.encode(request.getPassword()))
                                   .userName(request.getUserName())
                                   .role("ROLE_USER")
                                   .build();
           
           this.repo.save(newUser); 
        } else {
           throw new UserAlreadyExistsException("User already exists with username " + request.getUserName());
       }
        
    }
    
}
