/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vivek.jwt_authentication_example.Service;

import com.vivek.jwt_authentication_example.Entity.UserInfo;
import com.vivek.jwt_authentication_example.Request.UserRegistrationRequest;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Vivek
 */
public interface UserService {
    
    public UserInfo findByUserName(String username);
    
    public boolean UserExist(String username);
    
    public void createUser(UserRegistrationRequest newUser);
      
}
