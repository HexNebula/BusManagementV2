package com.microservices.businformationservice.repository;

import com.microservices.businformationservice.model.BusInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusInformationRepository extends JpaRepository<BusInformation, Long> {
}
