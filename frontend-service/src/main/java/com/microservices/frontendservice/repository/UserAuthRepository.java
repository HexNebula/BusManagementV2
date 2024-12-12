package com.microservices.frontendservice.repository;

import com.microservices.frontendservice.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    Optional<UserAuth> findByUserName(String userName);
}
