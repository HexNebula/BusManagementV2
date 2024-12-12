package com.microservices.frontendservice.controller;

import com.microservices.frontendservice.model.UserDetails;
import com.microservices.frontendservice.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/airline_reservation/api")
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/getAllUserDetails")
    public ResponseEntity<?> getAllUsers() {
       return userDetailsService.getAllUserDetails();
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return userDetailsService.getUserDetailsById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDetails userDetails) {
        return userDetailsService.createUserDetails(userDetails);
    }

    // Endpoint to update an existing user by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDetails userDetails) {
        return userDetailsService.updateUserDetails(id, userDetails);
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        return userDetailsService.deleteUserDetails(id);
    }

}
