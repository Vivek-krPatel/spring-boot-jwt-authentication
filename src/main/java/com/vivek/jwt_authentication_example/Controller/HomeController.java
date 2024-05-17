/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vivek.jwt_authentication_example.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vivek
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class HomeController {
    
    @GetMapping("/home")
    String greet(Authentication auth) {
        log.info("INFO : Inside greet method of Home Controller");
        log.info("INFO : protected endpoint called with principal {}",auth.getPrincipal());
        return "HELLO FROM JWT " + auth.getName();
    }
    
}
