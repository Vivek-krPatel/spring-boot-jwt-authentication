/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vivek.jwt_authentication_example.Request;

import lombok.Data;

/**
 *
 * @author Vivek
 */
@Data
public class UserRegistrationRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    String userName; 
}
