/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vivek.jwt_authentication_example.Configuration;

import com.vivek.jwt_authentication_example.Entity.UserInfo;
import com.vivek.jwt_authentication_example.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vivek
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }
    
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo result = this.repo.findByUserNameOrEmail(username, username).get();
        org.springframework.security.core.userdetails.UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(result.getUsername())
                .password(result.getPassword())
                .roles(result.getAuthorities().toString())
                .build();
        
        return user; 
    }
    
}
