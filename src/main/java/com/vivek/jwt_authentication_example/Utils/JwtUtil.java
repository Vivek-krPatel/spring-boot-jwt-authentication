/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vivek.jwt_authentication_example.Utils;

import com.vivek.jwt_authentication_example.Entity.UserInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;


/**
 *
 * @author Vivek
 */
@Component
public class JwtUtil {
    //@Value("${jwt.secret}")
    private String secretKey = "my_key";
    private static long expiryDuration = 60 * 60; 
    
    
    public String generateToken(UserInfo user) {
        long millitime = System.currentTimeMillis();
        long expiryTime = millitime + expiryDuration * 1000;
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(millitime))
                .setExpiration(new Date(expiryTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }
    
    public boolean validateToken(String token, UserInfo userDetails) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

}

