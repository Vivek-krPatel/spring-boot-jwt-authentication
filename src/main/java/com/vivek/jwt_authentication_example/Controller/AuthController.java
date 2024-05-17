/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vivek.jwt_authentication_example.Controller;

import com.vivek.jwt_authentication_example.Entity.UserInfo;
import com.vivek.jwt_authentication_example.Exception.UserAlreadyExistsException;
import com.vivek.jwt_authentication_example.Request.AuthRequest;
import com.vivek.jwt_authentication_example.Request.UserRegistrationRequest;
import com.vivek.jwt_authentication_example.Response.TokenResponse;
import com.vivek.jwt_authentication_example.Service.UserService;
import com.vivek.jwt_authentication_example.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vivek
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
   
    private final AuthenticationManager manager;
    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager manager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.manager = manager;
        this.userService = userService;
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody AuthRequest req) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(req.getUsername(), req.getPassword());
        try {
            Authentication authenticationResponse = this.manager.authenticate(authenticationRequest);
            UserInfo user = this.userService.findByUserName(authenticationResponse.getName());
            String token = jwtUtil.generateToken(user);
            TokenResponse response = TokenResponse.builder()
                                    .success(true)
                                    .Token(token)
                                    .build();
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or password");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request) {
       try  {
           this.userService.createUser(request);
           return ResponseEntity.ok("User created");
       } catch (UserAlreadyExistsException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
       }
    }
        
}

