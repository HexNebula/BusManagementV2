package com.microservices.businformationservice.repository;

import com.microservices.businformationservice.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
}
