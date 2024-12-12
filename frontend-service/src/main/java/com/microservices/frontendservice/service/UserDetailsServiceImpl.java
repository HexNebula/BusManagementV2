package com.microservices.frontendservice.service;

import com.microservices.frontendservice.dto.ApiResponse;
import com.microservices.frontendservice.model.UserDetails;
import com.microservices.frontendservice.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserDetailsRepository userDetailsRepository;


    @Override
    public ResponseEntity<?> getAllUserDetails() {
        return ResponseEntity.ok().body(new ApiResponse(true, HttpStatus.OK.value(),userDetailsRepository.findAll()));
    }

    @Override
    public ResponseEntity<?> getUserDetailsById(int id) {
        return ResponseEntity.ok().body(new ApiResponse(true, HttpStatus.OK.value(),userDetailsRepository.findById(id)));
    }
    @Override
    public ResponseEntity<?> createUserDetails(UserDetails userDetails) {
        UserDetails createdUserDetails = userDetailsRepository.save(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDetails);
    }

    @Override
    public ResponseEntity<?> updateUserDetails(int id, UserDetails userDetails) {
        Optional<UserDetails> existingUserDetails = userDetailsRepository.findById(id);

        if (existingUserDetails.isPresent()) {
            UserDetails updatedUser = existingUserDetails.get();
            updatedUser.setUserName(userDetails.getUserName());
            updatedUser.setFirstName(userDetails.getFirstName());
            updatedUser.setLastName(userDetails.getLastName());
            updatedUser.setFullName(userDetails.getFullName());
            updatedUser.setNic(userDetails.getNic());
            updatedUser.setPassportId(userDetails.getPassportId());
            updatedUser.setCountry(userDetails.getCountry());
            updatedUser.setBirthDay(userDetails.getBirthDay());
            updatedUser.setPhoneNumber(userDetails.getPhoneNumber());
            updatedUser.setEmail(userDetails.getEmail());
            userDetailsRepository.save(updatedUser);

            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteUserDetails(int id) {
        if (userDetailsRepository.existsById(id)) {
            userDetailsRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
