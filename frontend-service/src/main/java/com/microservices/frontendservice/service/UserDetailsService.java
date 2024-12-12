package com.microservices.frontendservice.service;

import com.microservices.frontendservice.model.UserDetails;
import org.springframework.http.ResponseEntity;

public interface UserDetailsService {
    public ResponseEntity<?> getAllUserDetails();
    public ResponseEntity<?> getUserDetailsById(int id);
    public ResponseEntity<?> createUserDetails(UserDetails userDetails);
    public ResponseEntity<?> updateUserDetails(int id, UserDetails userDetails);
    public ResponseEntity<?> deleteUserDetails(int id);
}
