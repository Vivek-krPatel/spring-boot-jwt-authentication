/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vivek.jwt_authentication_example.Repository;

import com.vivek.jwt_authentication_example.Entity.UserInfo;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Vivek
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByEmail(String email);
    Optional<UserInfo> findByUserName(String username);
    boolean existsByUserName(String username);
    Optional<UserInfo> findByUserNameOrEmail(String username, String email);
    
}
